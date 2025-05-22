
import java.util.ArrayList;
import java.util.List;


public class Turma {

    private String professor;
    private String semestre;
    private String avaliacao;
    private String tipoDeAula;
    private Integer sala;
    private Integer horario;
    private Integer capacidadeMax;
    private Disciplina disciplina;
    private List<Aluno> listaDeAlunos;
    private Integer numeroDaTurma;
    private Integer vagas;

    public Turma() {
        this.professor = "";
        this.semestre = "";
        this.avaliacao = "";
        this.tipoDeAula = "";
        this.sala = 0;
        this.horario = 0;
        this.capacidadeMax = 0;
        this.numeroDaTurma = 0;
        this.vagas = 0;
    }
    
    public Turma(String professor, String  semestre, String avaliacao, String tipoDeAula, Integer sala, Integer horario, Integer capacidadeMax, Disciplina disciplina, Integer numeroDaTurma) {
        this.professor = professor;
        this.semestre = semestre;
        this.avaliacao = avaliacao;
        this.tipoDeAula = tipoDeAula;
        this.sala = sala;
        this.horario = horario;
        this.capacidadeMax = capacidadeMax;
        this.disciplina = disciplina;
        this.numeroDaTurma = numeroDaTurma;
        this.vagas = capacidadeMax;
        this.listaDeAlunos = new ArrayList<>();
    }
    
    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTipoDeAula() {
        return tipoDeAula;
    }

    public void setTipoDeAula(String tipoDeAula) {
        this.tipoDeAula = tipoDeAula;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Integer getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(Integer capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public List<Aluno> getListaAlunos() {
        return listaDeAlunos;
    }

    public Integer getNumeroDaTurma() {
        return numeroDaTurma;
    }

    public void setNumeroDaTurma(Integer numeroDaTurma) {
        this.numeroDaTurma = numeroDaTurma;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }
}
