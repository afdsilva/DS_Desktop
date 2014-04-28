/*
 * Sistac
 * Classe Categoria
 * Autor: Andre Silva
 * e-mail: afdsilva@inf.ufpel.edu.br
 */

package provisorio;

import java.util.ArrayList;

/**
 *
 * @author andref
 */

public class Categoria {
    
    private String nome;
    private Integer maxHoras;
    public Categoria() {
        this.nome = null;
        this.maxHoras = null;
    }
    public Categoria(String nome, Integer maxHoras) {
        this.nome = nome;
        this.maxHoras = maxHoras;
    }
    
    public Categoria(Categoria copia) {
        this.nome = copia.getNome();
        this.maxHoras = copia.getMaxHoras();
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the maxHoras
     */
    public Integer getMaxHoras() {
        return maxHoras;
    }

    /**
     * @param maxHoras the maxHoras to set
     */
    public void setMaxHoras(Integer maxHoras) {
        this.maxHoras = maxHoras;
    }
    
    static ArrayList<Categoria> listaCategorias;
    /**
     * Carrega as categorias do arquivo de configuracao para uma classe estatica 
     * que pode ser acessada globalmente
     * Caso o metodo ja tenha sido invocado antes, irá limpar a lista e carregar novamente
     * os dados;
     */
    static void loadCategorias() {
        //cria ou limpa a lista de Categorias
        if (Categoria.listaCategorias == null)
            Categoria.listaCategorias = new ArrayList<>();
        else
            Categoria.listaCategorias.clear();
        Categoria pesquisa = new Categoria("Pesquisa", 100);
        Categoria.listaCategorias.add(pesquisa);
        Categoria extensao = new Categoria("Extensao", 100);
        Categoria.listaCategorias.add(extensao);
        Categoria ensino = new Categoria("Ensino", 100);
        Categoria.listaCategorias.add(ensino);
    }
    /**
     * Busca uma categoria na lista(consistencia dos dados)
     * @param procura
     * @return Categoria procurada, ou uma categoria dummy
     */
    static Categoria getCategoria(String procura) {
        for (Categoria categoria : listaCategorias) {
            if (categoria.getNome().toUpperCase().equals(procura.toUpperCase())) {
                return categoria;
            }
        }
        return new Categoria("Nao encontrado",0);
    }
}
