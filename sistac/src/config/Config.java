package config;

import java.util.logging.Logger;

public class Config {
    
    private static volatile Config instance = null;
    private final int width;
    private final int height;
    private Logger logger;
    
    private Config(){
        
        this.width = 800;
        this.height = 600;
        
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

    public int getWigth(){
        return this.width;
    }
        
    public int getHeight(){
        return this.height;
    }
    
    public Logger getLog() {
        return this.logger;
    }
}