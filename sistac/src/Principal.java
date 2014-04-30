

import system.*;
import config.*;


public class Principal {

    private Config config;
    private Janela telas;
   
    public Principal(){
        this.config = Config.getInstancia();
        this.telas = new Janela();
    }
    
    
    public static void main(String[] args) {
        
        Principal sistac = new Principal();
        sistac.comecar();

    }
    
    public void comecar(){
    
       this.telas.setVisible(true);
    }
}