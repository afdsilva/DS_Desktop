/*
 * Sistac
 * Classe TipoAtividade
 * Autor: Andre Silva
 * e-mail: afdsilva@inf.ufpel.edu.br
 */
package system;

import java.util.ArrayList;
import config.*;

/**
 *
 * @author andref
 */
public class TipoAtividade {

    private static ArrayList<TipoAtividade> listaTipoAtividades;

    /**
     * @return the listaTipoAtividades
     */
    public static ArrayList<TipoAtividade> getListaTipoAtividades() {
        return listaTipoAtividades;
    }

    /**
     * @param aListaTipoAtividades the listaTipoAtividades to set
     */
    public static void setListaTipoAtividades(ArrayList<TipoAtividade> aListaTipoAtividades) {
        listaTipoAtividades = aListaTipoAtividades;
    }

    private Integer cod;
    private String descricao;
    private Integer minHoras;
    private Integer maxHoras;
    private Categoria categoria;
    private String unidadeTipoAtividade;

    public TipoAtividade() {
        this.cod = null;
        this.descricao = null;
        this.minHoras = 0;
        this.maxHoras = 0;
        this.categoria = null;
        this.unidadeTipoAtividade = null;
    }

    public TipoAtividade(String descricao, Integer minHoras, Integer maxHoras, Categoria categoria, String unidadeTipoAtividade) {
        this.descricao = descricao;
        this.cod = null;
        this.minHoras = minHoras;
        this.maxHoras = maxHoras;
        this.categoria = categoria;
        this.unidadeTipoAtividade = unidadeTipoAtividade;
    }

    public TipoAtividade(Integer cod, String descricao, Integer minHoras, Integer maxHoras, Categoria categoria, String unidadeTipoAtividade) {
        this.cod = cod;
        this.descricao = descricao;
        this.minHoras = minHoras;
        this.maxHoras = maxHoras;
        this.categoria = categoria;
        this.unidadeTipoAtividade = unidadeTipoAtividade;
    }
    public TipoAtividade(TipoAtividade copia) {
        this.cod = copia.getCod();
        this.descricao = copia.getDescricao();
        this.minHoras = copia.getMinHoras();
        this.maxHoras = copia.getMaxHoras();
        this.categoria = copia.getCategoria();
        this.unidadeTipoAtividade = copia.getUnidadeTipoAtividade();
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the unidadeTipoAtividade
     */
    public String getUnidadeTipoAtividade() {
        return unidadeTipoAtividade;
    }

    /**
     * @param unidadeTipoAtividade the unidadeTipoAtividade to set
     */
    public void setUnidadeTipoAtividade(String unidadeTipoAtividade) {
        this.unidadeTipoAtividade = unidadeTipoAtividade;
    }

    /**
     * Carrega os tipos de atividades do arquivo de configuracao para uma classe
     * estatica que pode ser acessada globalmente; Caso o metodo ja tenha sido
     * invocado antes, irá limpar a lista e carregar novamente os dados;
     */
    public static void loadTipoAtividades() {
        //cria ou limpa a lista de TipoAtividades
        if (TipoAtividade.getListaTipoAtividades() == null) {
            TipoAtividade.setListaTipoAtividades(new ArrayList<TipoAtividade>());
        } else {
            TipoAtividade.getListaTipoAtividades().clear();
        }
        if (Categoria.getListaCategorias() == null || Categoria.getListaCategorias().isEmpty()) {
            if(Curso.getCurso("Ciência da Computação").getCodigo() != 0){
                Categoria.loadCategorias("ccomp");
            } else {
                 if(Curso.getCurso("Engenharia de Computação").getCodigo() != 0){
                    Categoria.loadCategorias("ecomp");
                 }
            }
        }
        JsonParser json = new JsonParser();
        TipoAtividade.listaTipoAtividades.addAll(json.getAllTypesOfActivity("ccomp"));
        
    }

    /**
     * Busca um tipo de atividade na lista (consistencia dos dados)
     *
     * @param descricao
     * @return TipoAtividade procurada, ou TipoAtividade dummy
     */
    public static TipoAtividade getTipoAtividade(String descricao) {
        for (TipoAtividade tipoAtividade : getListaTipoAtividades()) {
            if (tipoAtividade.getDescricao().toUpperCase().equals(descricao.toUpperCase())) {
                return tipoAtividade;
            }
        }
        return new TipoAtividade("Nao encontrado", 0, 0, new Categoria(), "");
    }

        /**
     * Busca um tipo de atividade na lista (consistencia dos dados)
     *
     * @param descricao
     * @return TipoAtividade procurada, ou TipoAtividade dummy
     */
    public static TipoAtividade getTipoAtividadeByCod(Integer cod) {
        for (TipoAtividade tipoAtividade : getListaTipoAtividades()) {
            if (tipoAtividade.getCod() == cod) {
                return tipoAtividade;
            }
        }
        return new TipoAtividade("Nao encontrado", 0, 0, new Categoria(), "");
    }
    
    /**
     * @return the minHoras
     */
    public Integer getMinHoras() {
        return minHoras;
    }

    /**
     * @param minHoras the minHoras to set
     */
    public void setMinHoras(Integer minHoras) {
        this.minHoras = minHoras;
    }

    /**
     * @return the cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
