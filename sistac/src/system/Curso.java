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
public class Curso {
    private String nome;
    private Integer codigo;
    private ArrayList<Categoria> listaCategoria;
    
    /**
     * Construtor Padrao
     */
    public Curso() {
        this.nome = "";
        this.codigo = null;
        this.listaCategoria = new ArrayList<>();
    }
    
    /**
     * Construtor passando nome, codigo e listaCategoria
     */
    
    public Curso(String nome, Integer codigo, ArrayList<Categoria> listaCategoria) {
        this.nome = nome;
        this.codigo = codigo;
        this.listaCategoria = listaCategoria;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public ArrayList<Categoria> getListaCategoria() {
        return listaCategoria;
    }
    
    public void setListaCategoria(ArrayList<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }
}    