/*
 * Sistac
 * Classe Aluno
 * Autor: Lidiane Costa
 * e-mail: lcdsilva@inf.ufpel.edu.br
 */

package system;

/**
 *
 * @author lidiane
 */
public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;
    
    /**
     * Construtor Padrao
     */
    public Aluno() {
        this.nome = null;
        this.matricula = null;
        this.curso = new Curso();
    }
    
    /**
     * Construtor passando nome, matricula e curso
     * @param nome
     * @param matricula
     * @param curso
     */
    
    public Aluno(String nome, String matricula, Curso curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
