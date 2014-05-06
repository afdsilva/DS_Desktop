package config;

/**
 * baixar a lib http://json-simple.googlecode.com/files/json-simple-1.1.1.jar e
 * add ao projeto criar as pastas base e save na raiz (DS_Desktop/sistac/) antes
 * de qualquer choro, aprendam JSON http://json.org/ para validar json
 * http://jsonlint.com/
 */

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.*;
import org.json.simple.parser.*;
import system.*;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import system.Curso;

/**
 *
 * @author gigamax13
 */
public class JsonParser {

    private final String root;
    private final Map<String, JSONObject> base;
    private final JSONParser parser;
    private final Logger log;
    private final Config config;

    public JsonParser() {
        this.root = Paths.get(".").toAbsolutePath().normalize().toString().concat("\\").replace("\\", "/");

        this.parser = new JSONParser();

        this.base = new HashMap<>();

        this.base.put("ccomp", this.parse("base/ccomp.json"));
        this.base.put("ecomp", this.parse("base/ecomp.json"));

        this.config = Config.getInstancia();
        this.log = config.getLog();
    }

    /**
     *
     * @param file nome do arquivo json a ser aberto
     * @return objeto com o conteudo do json
     */
    private JSONObject parse(String file) {
        Object obj = new Object();

        file = this.root.concat(file);

        try {
            FileReader arquivo = new FileReader(file);
            obj = this.parser.parse(arquivo);
            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    /**
     *
     * @param fileName nome do arquivo a ser salvo
     * @param obj json a ser salvo
     * @return true ou false
     */
    private boolean create(String fileName, JSONObject obj) {
        boolean created = false;

        fileName = this.root.concat(fileName);

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

            created = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return created;
    }

    /**
     *
     * @param course nome da base (ccomp ou ecomp)
     * @return base do curso
     */
    private JSONObject getBase(String course) {
        JSONObject base = null;

        try {
            base = this.base.get(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base;
    }

    /**
     * @param course nome da base (ccomp ou ecomp)
     * @return lista de categorias do curso
     */
    public final List<Categoria> getCategories(String course) {
        List<Categoria> categories = new ArrayList<>();
        JSONObject base = this.getBase(course);
        JSONObject obj;

        for (Object category : (JSONArray) base.get("category")) {
            obj = (JSONObject) category;
            categories.add(new Categoria((String) obj.get("name"), (int) (long) obj.get("minHr")));
        }

        return categories;
    }
    
    /**
     * Acessa o nome do curso no arquivo de configuração JSON.
     * @param course O curso em questão.
     * @return Nome do curso.
     */
    public final String getNomeCurso(String course){
        JSONObject jobj = this.getBase(course);
        
        return (String) jobj.get("name");
    }
    
    /**
     * Acessa o código do curso no arquivo de configuração JSON.
     * @param course O curso em questão.
     * @return O código do curso.
     */
    public final Integer getCodCurso(String course){
        JSONObject jobj = this.getBase(course);
        
        return (Integer) jobj.get("cod");
    }

    /**
     *
     * @param course nome do curso a ter sua base carregada (ccomp ou ecomp)
     * @param index index da categoria dentro do json (0 -> Ensino, 1 ->
     * Pesquisa, 2 -> Extensão)
     * @return lista de atividades da categoria index
     */
    public final List<TipoAtividade> getTypesOfActivity(String course, Integer index) {
        List<TipoAtividade> list = new ArrayList<>();
        JSONArray categoryArray = (JSONArray) this.getBase(course).get("category");
        JSONObject category = (JSONObject) categoryArray.get(index);
        JSONObject obj;

        for (Object activity : (JSONArray) category.get("activity")) {
            obj = (JSONObject) activity;

            list.add(new TipoAtividade((String) obj.get("name"), (int) (long) obj.get("hr"), (int) (long) obj.get("maxHr"), new Categoria((String) category.get("name"), (int) (long) category.get("minHr")), (String) obj.get("unit")));
        }

        return list;
    }

    /**
     *
     * @param course nome do curso a ter sua base carregada (ccomp ou gcomp)
     * @return lista de atividades de todas as categorias
     */
    public final List<TipoAtividade> getAllTypesOfActivity(String course) {
        List<TipoAtividade> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.addAll(this.getTypesOfActivity(course, i));
        }

        return list;
    }

    /**
     *
     * @param request pedido a ser salvo
     * @return boolean para saber se o arquivo foi criado
     */
    public boolean saveRequest(Pedido request) {
        JSONObject json = new JSONObject();
        JSONObject temp;

        json.put("name", request.getAluno().getNome());
        json.put("registry", request.getAluno().getMatricula());
        json.put("course", request.getAluno().getCurso().getNome());
        json.put("cod", request.getAluno().getCurso().getCodigo());
        json.put("year", request.getAno());
        json.put("semester", request.getSemestre());

        JSONArray list = new JSONArray();

        for (Atividade activity : request.getListaAtividadesComplementares()) {
            temp = new JSONObject();

            temp.put("description", activity.getDescricao());
            temp.put("typeOfActivity", activity.getTipoAtividade().getDescricao());
            temp.put("category", activity.getCategoria().getNome());
            temp.put("time", activity.getUnidadeAtividadeAproveitada());

            list.add(temp);
        }

        json.put("activity", list);

        String file = "save/";

        file = file.concat(request.getAluno().getMatricula());

        file = file.concat(".json");

        return this.create(file, json);
    }

    /**
     *
     * @param dir diretorio onde procurar os json's
     * @return lista de nomes
     */
    private ArrayList<String> listOfFiles(String dir) {
        File directory = new File(dir);
        ArrayList<String> files = new ArrayList<>();
        String fileName, extension;

        for (File file : (File[]) directory.listFiles()) {
            fileName = file.getName();
            extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

            if (file.isFile() && extension.equals("json")) {
                files.add(fileName+".json");
            }
        }

        return files;
    }

    /**
     *
     * @param fileName nome do arquivo json a ser carregado
     * @return pedido com alguns atributos/objetos nulos, usar os dados
     * carregados previamente pelo loader
     */
    public Pedido loadRequest(String fileName) {
        log.info(fileName);
        JSONObject obj, json = this.parse(fileName);

        Curso course = new Curso((String) json.get("course"), (int) (long) json.get("cod"), null);
        Aluno student = new Aluno((String) json.get("name"), (String) json.get("registry"), course);
        List<Atividade> list = new ArrayList<Atividade>();
        Atividade tempAtividade;
        TipoAtividade tempTipoAtividade;
        Categoria tempCategoria;

        for (Object activity : (JSONArray) json.get("activity")) {
            obj = (JSONObject) activity;
            
            
            tempAtividade = new Atividade();
            tempTipoAtividade = new TipoAtividade();
            tempCategoria = new Categoria();
            
            tempAtividade.setDescricao((String) obj.get("description"));
            tempAtividade.setUnidadeAtividade((int) (long) obj.get("time"));
            
            tempCategoria.setNome((String) obj.get("category"));
            tempTipoAtividade.setDescricao((String) obj.get("typeOfActivity"));
            tempTipoAtividade.setCategoria(tempCategoria);
            
            tempAtividade.setTipoAtividade(tempTipoAtividade);
            tempAtividade.setCategoria(tempCategoria);

            list.add(tempAtividade);
        }

        Pedido request = new Pedido(student, (int) (long) json.get("year"), (int) (long) json.get("semester"), (ArrayList<Atividade>) list);

        return request;
    }

    // acessa a pasta save
    // Retorna uma lista com o nome dos arquivos existentes no diretório. 
    public ArrayList<Pedido> parseFilesToJSON() {
        ArrayList<Pedido> listOfPedidos = new ArrayList<Pedido>();
        File files = new File("save/");
        File afile[] = files.listFiles();
        for (int i = 0; i < afile.length; i++) {
            if(afile[i].getName().endsWith(".json")){
                listOfPedidos.add(loadRequest("save/"+afile[i].getName()));
            }
        }
        return listOfPedidos;
    }

    /**
     * remove arquivos na pasta save
     *
     * @param nome
     * @return true or false
     */
    public boolean removerArquivo(String nome) {
        Integer reply;
        String file;

        System.out.println(nome);

        file = this.root.concat("save/" + nome + ".json");
        File f = new File(file);
        System.out.println(f);
        reply = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja exluir seu pedido?", "Remover?",  JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION){
            try{
                f.delete();
                System.out.println("Arquivo deletado com sucesso.");
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Não foi possível deletar o arquivo.");
        return false;
    }

    /**
     * Usado para teste, pode ser removido
     */
    public static void main(String[] args) {
//        JsonParser jparser = new JsonParser();
//        JSONObject jobj = jparser.getBase("ecomp");
//        System.out.println("Nome: " + jobj.get("name") + " Código: " + jobj.get("cod") + "\n");
    }

}
