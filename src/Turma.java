
public class Turma {

    private String professor;
    private Integer semestre;
    private String avaliacao;
    private String tipoDeAula;
    private Integer sala;
    private Integer horario;
    private Integer capacidadeMax;

    public Turma() {
        this.professor = "";
        this.semestre = 0;
        this.avaliacao = "";
        this.tipoDeAula = "";
        this.sala = 0;
        this.horario = 0;
        this.capacidadeMax = 0;
    }

    public Turma(String professor, Integer semestre, String avaliacao, String tipoDeAula, Integer sala, Integer horario, Integer capacidadeMax) {
        this.professor = professor;
        this.semestre = semestre;
        this.avaliacao = avaliacao;
        this.tipoDeAula = tipoDeAula;
        this.sala = sala;
        this.horario = horario;
        this.capacidadeMax = capacidadeMax;
    }
    
    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
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
}
