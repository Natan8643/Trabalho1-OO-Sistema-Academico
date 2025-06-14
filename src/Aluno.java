
import java.util.ArrayList;
import java.util.List;


public class Aluno {

    private String nome;
    private Integer matricula;
    private String curso;
    private Boolean cursoTrancado;
    private Boolean semestreTrancado;
    private Boolean especial;
    private Integer materia;
    private List<String> preRequisito;

    public Aluno() {
        this.nome = "";
        this.matricula = 0;
        this.curso = "";
        this.cursoTrancado = false;
        this.semestreTrancado = false;
        this.especial = false;
        this.materia = 0;

    }

    public Aluno(String nome, Integer matricula, String curso, Boolean especial, Boolean cursoTrancado, Boolean semestreTrancado) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.cursoTrancado = cursoTrancado;
        this.semestreTrancado = semestreTrancado;
        this.especial = especial;
        this.materia = 0;
        this.preRequisito = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getCursoTrancado() {
        return cursoTrancado;
    }

    public void setCursoTrancado(Boolean cursoTrancado) {
        this.cursoTrancado = cursoTrancado;
    }

    public Boolean getSemestreTrancado() {
        return semestreTrancado;
    }

    public void setSemestreTrancado(Boolean semestreTrancado) {
        this.semestreTrancado = semestreTrancado;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }

    public Integer getMateria() {
        return materia;
    }

    public void setMateria(Integer materia) {
        this.materia = materia;
    }
    
    public List<String> getPreRequisito() {
    return preRequisito;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Matricula: " + getMatricula() + ", Curso: " + getCurso() + ", Especial: " + getEspecial() + ", Semestre trancado: " + getSemestreTrancado() + ", Curso trancado: " + getCursoTrancado() + ", Pré-requisitos: " + getPreRequisito();
    }
}
