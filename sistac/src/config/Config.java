package config;

import java.util.logging.Logger;
import java.awt.Font;

public class Config {

    private static volatile Config instance = null;
    private final int width;
    private final int height;
    private final Font fonteTexto;
    private final Font fonteTitulo;
    private final Font fonteLabel;
    private Logger logger;

    private Config() {

        this.width = 1024;
        this.height = 768;
        this.fonteTexto = new Font("Tahoma", Font.PLAIN, 14);
        this.fonteTitulo = new Font("Tahoma", Font.BOLD, 17);
        this.fonteLabel = new Font("Tahoma", Font.PLAIN, 15);
        this.logger = Logger.getLogger(Config.class.getName());
    }

    /**
     * Retorna o singleton do tipo Lazy initialization
     *
     * @return
     */
    public static Config getInstancia() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Logger getLog() {
        return this.logger;
    }

    public Font getFontTexto() {
        return fonteTexto;
    }
    
    public Font getFontTitulo(){
        return fonteTitulo;
    }
    
    public Font getFontLabel(){
        return fonteLabel;
    }
}
