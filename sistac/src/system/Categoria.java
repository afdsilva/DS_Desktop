/*
 * Sistac
 * Classe Categoria
 * Autor: Andre Silva
 * e-mail: afdsilva@inf.ufpel.edu.br
 */
package system;

import java.util.ArrayList;
import config.*;
import java.util.logging.Logger;
/**
 *
 * @author andref
 */
public class Categoria {

    private static ArrayList<Categoria> listaCategorias;

    /**
     * @return the listaCategorias
     */
    public static ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * @param aListaCategorias the listaCategorias to set
     */
    public static void setListaCategorias(ArrayList<Categoria> aListaCategorias) {
        listaCategorias = aListaCategorias;
    }

    private String cod;
    private String nome;
    private Integer cargaHoraria;
    private Config config;
    private static Logger log;
    
    public Categoria() {
        this.nome = null;
        this.cod = null;
        this.cargaHoraria = null;
        this.config = Config.getInstancia();
        this.log = config.getLog();
    }

    public Categoria(String nome, Integer cargaHoraria) {
        this.nome = nome;
        this.cod = nome;
        this.cargaHoraria = cargaHoraria;
        this.config = Config.getInstancia();
        this.log = config.getLog();
    }

    public Categoria(String cod, String nome, Integer cargaHoraria) {
        this.cod = cod;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.config = Config.getInstancia();
        this.log = config.getLog();
    }
    
    public Categoria(Categoria copia) {
        this.cod = copia.getCod();
        this.nome = copia.getNome();
        this.cargaHoraria = copia.getCargaHoraria();
        this.config = Config.getInstancia();
        this.log = config.getLog();
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
     * @return the cargaHoraria
     */
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param maxHoras the cargaHoraria to set
     */
    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * Carrega as categorias do arquivo de configuracao para uma classe estatica
     * que pode ser acessada globalmente Caso o metodo ja tenha sido invocado
     * antes, irá limpar a lista e carregar novamente os dados;
     */
    public static void loadCategorias(String curso) {
        JsonParser json = new JsonParser();
        //cria ou limpa a lista de Categorias
        if (Categoria.getListaCategorias() == null) {
            Categoria.setListaCategorias(new ArrayList<Categoria>());
        } else {
            Categoria.getListaCategorias().clear();
        }
        // traz do arquivo de configuração e joga pra dentro da lista da classe
        Categoria.listaCategorias.addAll(json.getCategories(curso));
        
        
        for (int i = 0; i < Categoria.listaCategorias.size(); i++){
            log.info(Categoria.listaCategorias.get(i).getNome());
        }
    }

    /**
     * Busca uma categoria na lista(consistencia dos dados)
     *
     * @param procura
     * @return Categoria procurada, ou uma categoria dummy
     */
    public static Categoria getCategoria(String procura) {
        for (Categoria categoria : getListaCategorias()) {
            if (categoria.getNome().toUpperCase().equals(procura.toUpperCase())) {
                return categoria;
            }
        }
        return new Categoria("nulo", "Nao encontrado", 0);
    }

    public static Categoria getCategoriaCod(String procura) {
        for (Categoria categoria : getListaCategorias()) {
            if (categoria.getCod().toUpperCase().equals(procura.toUpperCase())) {
                return categoria;
            }
        }
        return new Categoria("nulo", "Nao encontrado", 0);
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

}
