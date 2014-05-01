package config;
/**
 * baixar a lib http://json-simple.googlecode.com/files/json-simple-1.1.1.jar e add ao projeto
 * criar as pastas base e save na raiz (DS_Desktop/sistac/)
 * antes de qualquer choro, aprendam JSON http://json.org/
 * para validar json http://jsonlint.com/
*/

import system.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author gigamax13
 */
public class JsonParser {
    private final String root;
    private final JSONObject ccompBase;
    //private JSONObject gcompBase;
    private final JSONParser parser;
    
    
    public JsonParser(){
        this.root = Paths.get(".").toAbsolutePath().normalize().toString().concat("\\");
        
        System.out.println(this.root);
        
        this.parser = new JSONParser();
        
        this.ccompBase = this.parse("base/ccomp.json");
        //this.gcompBase = this.parse("base/ccomp.json");
        
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
    
    public final List<Categoria> getCategories(){
        List<Categoria> categories = new ArrayList<>();
        
        JSONObject obj;
        
        for (Object category : (JSONArray) this.ccompBase.get("category")) {
            obj = (JSONObject) category;
            categories.add(new Categoria((String) obj.get("name"), (int) (long) obj.get("minHr")));
        }
        
        return categories;
    }
    
    private void test(){
        List<Categoria> lol = this.getCategories();
        
        for (Categoria category : lol){
            System.out.println(category.getNome());
            System.out.println(category.getMaxHoras());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        new JsonParser();
    }
    
}