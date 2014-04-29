/*
 * Sistac
 * Classe TipoAtividade
 * Autor: Andre Silva
 * e-mail: afdsilva@inf.ufpel.edu.br
 */

package system;

import java.util.ArrayList;

/**
 *
 * @author andref
 */
public class TipoAtividade {
    
    private String descricao;
    private Integer maxHoras;
    private Categoria categoria;
    private String unidadeTipoAtividade;
    
    public TipoAtividade() {
        this.descricao = null;
        this.maxHoras = 0;
        this.categoria = null;
        this.unidadeTipoAtividade = null;
    }
    public TipoAtividade(String descricao, Integer maxHoras, Categoria categoria, String unidadeTipoAtividade) {
        this.descricao = descricao;
        this.maxHoras = maxHoras;
        this.categoria = categoria;
        this.unidadeTipoAtividade = unidadeTipoAtividade;
    }
    
    public TipoAtividade(TipoAtividade copia) {
        this.descricao = copia.descricao;
        this.maxHoras = copia.maxHoras;
        this.categoria = copia.categoria;
        this.unidadeTipoAtividade = copia.unidadeTipoAtividade;
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
    
    static ArrayList<TipoAtividade> listaTipoAtividades;
    
    /**
     * Carrega os tipos de atividades do arquivo de configuracao para uma classe estatica 
     * que pode ser acessada globalmente;
     * Caso o metodo ja tenha sido invocado antes, irá limpar a lista e carregar novamente
     * os dados;
     */
    public static void loadTipoAtividades() {
        //cria ou limpa a lista de TipoAtividades
        if (TipoAtividade.listaTipoAtividades == null )
            TipoAtividade.listaTipoAtividades = new ArrayList<>();
        else
            TipoAtividade.listaTipoAtividades.clear();
        if (Categoria.listaCategorias == null || Categoria.listaCategorias.isEmpty())
           Categoria.loadCategorias();
        Categoria pesquisa = Categoria.getCategoria("pesquisa");
        Categoria ensino = Categoria.getCategoria("ensino");
        Categoria extensao = Categoria.getCategoria("extensao");
        
        TipoAtividade.listaTipoAtividades = new ArrayList<>();
        TipoAtividade.listaTipoAtividades.add(new TipoAtividade("Monitoria", 51, ensino, "horas"));
        TipoAtividade.listaTipoAtividades.add(new TipoAtividade("Participação em Eventos Científicos Internacionais", 51, pesquisa, "unidade"));
        TipoAtividade.listaTipoAtividades.add(new TipoAtividade("Bolsa de Graduação da UFPel", 200, extensao, "horas"));
    }
    
    /**
     * Busca um tipo de atividade na lista (consistencia dos dados)
     * @param descricao
     * @return TipoAtividade procurada, ou TipoAtividade dummy
     */
    public static TipoAtividade getTipoAtividade(String descricao) {
        for(TipoAtividade tipoAtividade : listaTipoAtividades) {
            if (tipoAtividade.getDescricao().toUpperCase().equals(descricao.toUpperCase())) {
                return tipoAtividade;
            }
        }
        return new TipoAtividade("Nao encontrado", 0, new Categoria(), "");
    }
    
}