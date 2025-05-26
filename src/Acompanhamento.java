
public class Acompanhamento {

    private Aluno aluno;
    private Turma turma;
    private double notaP1;
    private double notaP2;
    private double notaP3;
    private double seminario;
    private double listaDeExercicios;
    private Integer presenca;
    private double mediaFinal;
    private boolean aprovacao;
    private Disciplina disciplina;

    public Acompanhamento() {
        this.aluno = new Aluno();
        this.turma = new Turma();
        this.notaP1 = 0;
        this.notaP2 = 0;
        this.notaP3 = 0;
        this.seminario = 0;
        this.listaDeExercicios = 0;
        this.presenca = 0;
        this.mediaFinal = 0;
        this.aprovacao = false;
    }

    public Acompanhamento(Aluno aluno, int matricula, String materia, Turma turma, double notaP1, double notaP2, double notaP3, double seminario, double listaDeExercicios, Integer presenca, double mediaFinal, boolean aprovacao) {
        this.aluno = aluno;
        this.turma = turma;
        this.notaP1 = notaP1;
        this.notaP2 = notaP2;
        this.notaP3 = notaP3;
        this.seminario = seminario;
        this.listaDeExercicios = listaDeExercicios;
        this.presenca = presenca;
        this.mediaFinal = mediaFinal;
        this.aprovacao = aprovacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public double getNotaP1() {
        return notaP1;
    }

    public void setNotaP1(double notaP1) {
        this.notaP1 = notaP1;
    }

    public double getNotaP2() {
        return notaP2;
    }

    public void setNotaP2(double notaP2) {
        this.notaP2 = notaP2;
    }

    public double getNotaP3() {
        return notaP3;
    }

    public void setNotaP3(double notaP3) {
        this.notaP3 = notaP3;
    }

    public double getSeminario() {
        return seminario;
    }

    public void setSeminario(double seminario) {
        this.seminario = seminario;
    }

    public double getListaDeExercicios() {
        return listaDeExercicios;
    }

    public void setListaDeExercicios(double listaDeExercicios) {
        this.listaDeExercicios = listaDeExercicios;
    }

    public Integer getPresenca() {
        return presenca;
    }

    public void setPresenca(Integer presenca) {
        this.presenca = presenca;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public boolean getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(boolean aprovacao) {
        this.aprovacao = aprovacao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
