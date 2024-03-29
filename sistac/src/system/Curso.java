/*
 * Sistac
 * Classe Curso
 * Autor: Lidiane Costa
 * e-mail: lcdsilva@inf.ufpel.edu.br
 */
package system;

import java.util.*;
import config.*;
import java.util.logging.Logger;
/**
 *
 * @author lidiane
 */
public class Curso {

    private static ArrayList<Curso> listaCursos;

    public static ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }

    public static void setListaCursos(ArrayList<Curso> aListaCursos) {
        listaCursos = aListaCursos;
    }

    private String nome;
    private Integer codigo;
    private ArrayList<Categoria> listaCategorias;
    private Config config;
    private static Logger log;
    
    /**
     * Construtor Padrao
     */
    public Curso() {
        this.nome = null;
        this.codigo = null;
        this.listaCategorias = new ArrayList<>();
        this.config = Config.getInstancia();
        this.log = config.getLog();
    }

    /**
     * Construtor passando nome, codigo e listaCategorias
     *
     * @param nome
     * @param codigo
     * @param listaCategorias
     */
    public Curso(String nome, Integer codigo, ArrayList<Categoria> listaCategorias) {
        this.nome = nome;
        this.codigo = codigo;
        this.listaCategorias = listaCategorias;
        this.config = Config.getInstancia();
        this.log = config.getLog();
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

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias.addAll(listaCategorias);
    }

    public static void loadCursos() {
        //cria ou limpa a lista de Cursos
        if (Curso.getListaCursos() == null) {
            Curso.setListaCursos(new ArrayList<Curso>());
        } else {
            Curso.getListaCursos().clear();
        }
        //conferir código de cada curso
        Curso cienciaDaComputacao = new Curso("Ciência da Computação", 1, Categoria.getListaCategorias());
        Curso.getListaCursos().add(cienciaDaComputacao);
        Curso engenhariaDaComputacao = new Curso("Engenharia de Computação", 2, Categoria.getListaCategorias());
        Curso.getListaCursos().add(engenhariaDaComputacao);
    }

    public static Curso getCurso(String procura) {
        for (Curso curso : getListaCursos()) {
            if (curso.getNome().toUpperCase().equals(procura.toUpperCase())) {
                return curso;
            }
        }
        return new Curso("Nao encontrado", 0, null);
    }
}
