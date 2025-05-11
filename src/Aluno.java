

public class Aluno {
    private String nome;
    private Integer matricula;
    private String curso;
    private Boolean cursoTrancado;
    private Boolean semestreTrancado;

    public Aluno(){
        this.nome = "";
        this.matricula = 0;
        this.curso = "";
        this.cursoTrancado = false;
        this.semestreTrancado = false;

    }

    public Aluno(String nome, Integer matricula, String curso, Boolean cursoTrancado, Boolean semestreTrancado){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.cursoTrancado = cursoTrancado;
        this.semestreTrancado = semestreTrancado;
    }

    public String getNome(){
        return nome; 
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Integer getMatricula(){
        return matricula;
    }

    public void setMatricula(Integer matricula){
        this.matricula = matricula;
    }

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso){
        this.curso = curso;
    }

    public Boolean getCursoTrancado(){
        return cursoTrancado;
    }

    public void setCursoTrancado(Boolean cursoTrancado){
        this.cursoTrancado = cursoTrancado;
    }

    public Boolean getSemestreTrancado(){
        return semestreTrancado;
    }

    public void setSemestreTrancado(Boolean semestreTrancado){
        this.semestreTrancado = semestreTrancado;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Matricula: " + getMatricula() + ", Curso: " + getCurso() + ", Semestre trancado: " + getSemestreTrancado() + ", Curso trancado: " + getCursoTrancado();
    }
    
}


