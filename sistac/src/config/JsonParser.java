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
        
        this.test();
    }
    
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
     * @return lista de atividades
     */
    
    public final List<TipoAtividade> getTypesOfActivity(String course, Integer index){
        List<TipoAtividade> list = new ArrayList<>();
        JSONArray categoryArray = (JSONArray) this.getBase(course).get("category");
        JSONObject category = (JSONObject) categoryArray.get(index);
        JSONObject obj;
        
        for (Object activity : (JSONArray) category.get("activity")) {            
            obj = (JSONObject) activity;
            
            Categoria temp = new Categoria((String) category.get("name"), (int) (long) category.get("minHr"));
            
            list.add(new TipoAtividade((String) obj.get("name"), (int) (long) obj.get("hr"), (int) (long) obj.get("maxHr"), new Categoria((String) category.get("name"), (int) (long) category.get("minHr")), (String) obj.get("unit")));
        }
        
        return list;
    }
    
    private void test(){
        List<Categoria> teste1 = this.getCategories("ccomp");
        
        for(Categoria category : teste1){
            System.out.println(category.getNome());
        }
        
        List<TipoAtividade> teste2 = this.getTypesOfActivity("ccomp", 0);
        
        for(TipoAtividade activity : teste2){
            System.out.println(activity.getDescricao());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        new JsonParser();
    }
    
}