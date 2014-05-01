package config;
/**
 * baixar a lib http://json-simple.googlecode.com/files/json-simple-1.1.1.jar e add ao projeto
 * criar as pastas base e save na raiz (DS_Desktop/sistac/)
 * antes de qualquer choro, aprendam JSON http://json.org/
 * para validar json http://jsonlint.com/
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

/**
 *
 * @author gigamax13
 */
public class JsonParser {
    private final String root;
    private final Map <String, JSONObject> base;
    private final JSONParser parser;
    
    
    public JsonParser(){
        this.root = Paths.get(".").toAbsolutePath().normalize().toString().concat("\\");
        
        this.parser = new JSONParser();
        
        this.base = new HashMap<String, JSONObject>();
        
        this.base.put("ccomp", this.parse("base/ccomp.json"));
        this.base.put("gcomp", this.parse("base/gcomp.json"));
        
        // usado para teste, pode ser removido na versao final
        this.test();
    }
    
    /**
     * 
     * @param file nome do arquivo json a ser aberto
     * @return objeto com o conteudo do json
     */
    
    private JSONObject parse(String file){
        Object obj = new Object();
        
        file = this.root.concat(file);
        
        try {
            obj = this.parser.parse(new FileReader(file));
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
    
    private boolean create(String fileName, JSONObject obj){
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
     * @param course nome da base (ccomp ou gcomp)
     * @return base do curso
     */
    
    private JSONObject getBase(String course){
        JSONObject base = null;
        
        try{
            base = this.base.get(course);
        }catch(Exception e){
            e.printStackTrace();
        }
        return base;
    }
    
    /**
     * @param course nome da base (ccomp ou gcomp)
     * @return lista de categorias do curso
     */
    
    public final List<Categoria> getCategories(String course){
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
     * 
     * @param course nome do curso a ter sua base carregada (ccomp ou gcomp)
     * @param index index da categoria dentro do json (0 -> Ensino, 1 -> Pesquisa, 2 -> Extensão)
     * @return lista de atividades da categoria index
     */
    
    public final List<TipoAtividade> getTypesOfActivity(String course, Integer index){
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
    
    public final List<TipoAtividade> getAllTypesOfActivity(String course){
        List<TipoAtividade> list = new ArrayList<>();
        
        for(int i = 0; i < 3; i++){
            list.addAll(this.getTypesOfActivity(course, i));
        }
        
        return list;
    }
    
    /**
     * 
     * @param request pedido a ser salvo
     * @return boolean para saber se o arquivo foi criado
     */
    
    public boolean saveRequest(Pedido request){
        JSONObject json = new JSONObject();
        JSONObject temp;
        
        json.put("name", request.getAluno().getNome());
        json.put("registry", request.getAluno().getMatricula());
        json.put("course", request.getAluno().getCurso().getNome());
        json.put("cod", request.getAluno().getCurso().getCodigo());
        json.put("year", request.getAno());
        json.put("semester", request.getSemestre());
        
        JSONArray list = new JSONArray();
        
        for(Atividade activity : request.getListaAtividadesComplementares()){
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
     * Método que mostra como usar o parser, remova a sua chamada do construtor
     */
    
    private void test(){
        List<Categoria> teste1 = this.getCategories("ccomp");
        
        for(Categoria category : teste1){
            System.out.println(category.getNome());
        }
        
        List<TipoAtividade> teste2 = this.getTypesOfActivity("ccomp", 0);
        
        for(TipoAtividade activity : teste2){
            System.out.println(activity.getDescricao());
        }
        
        Curso curso = new Curso("Ciência da Computação", 313, (ArrayList<Categoria>) teste1);
        Aluno aluno = new Aluno("GigaMax13", "14101401", curso);
        List<Atividade> atividade = new ArrayList<>();
        
        atividade.add(new Atividade("Procrastinar", teste2.get(0), 313));
        
        Pedido teste3 = new Pedido(aluno, 2014, 9, (ArrayList<Atividade>) atividade);
        
        this.saveRequest(teste3);
    }

    /**
     * Usado para teste, pode ser removido
     */
    public static void main(String[] args) {        
        new JsonParser();
    }
    
}