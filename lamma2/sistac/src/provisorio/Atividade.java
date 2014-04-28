/*
 * Sistac
 * Classe Atividade
 * Autor: Andre Silva
 * e-mail: afdsilva@inf.ufpel.edu.br
 */

package provisorio;

/**
 *
 * @author andref
 */
public class Atividade {
    private String descricao;
    private TipoAtividade tipoAtividade;
    private Integer unidadeAtividade;
    private Categoria categoria;
    
    /**
     * Construtor Padrao
     */
    public Atividade() {
        this.descricao = "";
        this.tipoAtividade = new TipoAtividade();
        this.unidadeAtividade = 0;
        this.categoria = new Categoria();
    }
    /**
     * Construtor passando descricao e tipoAtividade, categoria é carregado com o valor default do tipoAtividade
     * @param descricao
     * @param tipoAtividade 
     */
    public Atividade(String descricao, TipoAtividade tipoAtividade) {
        this.descricao = descricao;
        this.tipoAtividade = tipoAtividade;
        this.unidadeAtividade = 0;
        this.categoria = tipoAtividade.getCategoria();
    }
    /**
     * Construtor de copia
     * @param copia 
     */
    public Atividade(Atividade copia) {
        this.descricao = copia.getDescricao();
        this.tipoAtividade = copia.getTipoAtividade();
        this.unidadeAtividade = copia.getUnidadeAtividade();
        this.categoria = copia.getCategoria();
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
     * @return the tipoAtividade
     */
    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    /**
     * @param tipoAtividade the tipoAtividade to set
     */
    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    /**
     * @return the unidadeAtividade
     */
    public Integer getUnidadeAtividade() {
        return unidadeAtividade;
    }

    /**
     * @param unidadeAtividade the unidadeAtividade to set
     */
    public void setUnidadeAtividade(Integer unidadeAtividade) {
        this.unidadeAtividade = unidadeAtividade;
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
}
