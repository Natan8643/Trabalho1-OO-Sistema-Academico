public class Aluno {
    private String nome;
    private Integer matricula;
    private String curso;

    public Aluno(){
        this.nome = "";
        this.matricula = 0;
        this.curso = "";
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

    public void setMatricula(int matricula){
        this.matricula = matricula;
    }

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso){
        this.curso = curso;
    }
}


