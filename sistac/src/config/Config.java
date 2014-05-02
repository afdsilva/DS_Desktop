package config;

import java.util.logging.Logger;
import java.awt.Font;

public class Config {
    
    private static volatile Config instance = null;
    private final int width;
    private final int height;
    private final Font fonteTexto;
    private Logger logger;
    
    private Config(){
        
        this.width = 800;
        this.height = 600;
        this.fonteTexto = new Font("Ubuntu", Font.PLAIN, 14);
        this.logger = Logger.getLogger(Config.class.getName());
    }
    
    /**
     * Retorna o singleton do tipo Lazy initialization
     * @return 
     */
    public static Config getInstancia() {
        if (instance == null) {
        	synchronized (Config.class){
        		if (instance == null) {
        			instance = new Config();
        		}
        	}
        }
        return instance;
    }

    public int getWidth(){
        return this.width;
    }
        
    public int getHeight(){
        return this.height;
    }
    
    public Logger getLog() {
        return this.logger;
    }
    
    public Font getFontTexto() {
        return fonteTexto;
    }
}