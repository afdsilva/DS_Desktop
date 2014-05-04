
import config.*;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import system.*;

public class Principal {

    private Config config;
    private Janela telas;
    private Logger log;

    public Principal() {
        this.config = Config.getInstancia();
        this.telas = new Janela();
        this.log = this.config.getLog();
    }

    public static void main(String[] args) {

        Principal sistac = new Principal();
        sistac.comecar();

    }
    /*
     Método que inicializa o sistema, carrega as combos e todas as funcionalidades
     do programa.
     */

    public void comecar() {
        this.telas.setVisible(true);
    }
}
