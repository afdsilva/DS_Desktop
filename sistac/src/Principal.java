



import system.Atividade;
import system.Atividade;
import system.TipoAtividade;
import system.TipoAtividade;

/**
 * Arquivo para testes iniciais com as classes Atividade, Categoria e TipoAtividade
 * @author andref
 */
public class Principal {

    /**
     * Shift+F6 (no netbeans) executa o arquivo aberto, isso ira executar essa classe provisoria ao inves
     * do projeto principal
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //carrega os tipos de atividades no sistema
        TipoAtividade.loadTipoAtividades();
        //carrega TipoAtividade em um objeto para facilir o uso
        TipoAtividade monitoria = TipoAtividade.getTipoAtividade("Monitoria");
        //cria um objeto atividade
        Atividade atividade = new Atividade("Monitoria de P1", monitoria);
        //dados recuperados dos objetos
        System.out.println("Descricao: " + atividade.getDescricao());
        System.out.println("TipoAtividade: " + atividade.getTipoAtividade().getDescricao());
        System.out.println("Unidade: " + atividade.getUnidadeAtividade() +" " + atividade.getTipoAtividade().getUnidadeTipoAtividade());
        System.out.println("Atividade Categoria: " + atividade.getTipoAtividade().getCategoria().getNome());
        
    }
    
}