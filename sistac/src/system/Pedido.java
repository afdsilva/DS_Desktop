/*
 * Sistac
 * Classe Aluno
 * Autor: Lidiane Costa
 * e-mail: lcdsilva@inf.ufpel.edu.br
 */

package system;

import java.util.*;

/**
 *
 * @author lidiane
 */
public class Pedido {
    private Aluno aluno;
    private Integer ano;
    private Integer semestre;
    private ArrayList<Atividade> listaAtividadesComplementares;
    
/**
     * Construtor Padrao
     */
    public Pedido() {
        this.aluno = null;
        this.ano = null;
        this.semestre = null;
        this.listaAtividadesComplementares = new ArrayList<>();
    }
    
    /**
     * Construtor passando aluno, ano, semestre e listaAtividadesComplementares
     */
    
    public Pedido(Aluno aluno, Integer ano, Integer semestre, ArrayList<Atividade> listaAtividadesComplementares) {
        this.aluno = aluno;
        this.ano = ano;
        this.semestre = semestre;
        this.listaAtividadesComplementares = listaAtividadesComplementares;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
    
    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
    
    public ArrayList<Atividade> getListaAtividadesComplementares() {
        return listaAtividadesComplementares;
    }
    
    public void setListaAtividadesComplementares(ArrayList<Atividade> listaAtividadesComplementares) {
        this.listaAtividadesComplementares = listaAtividadesComplementares;
    }
}
