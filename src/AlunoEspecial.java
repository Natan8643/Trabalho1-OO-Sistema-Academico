public class AlunoEspecial extends Aluno {
    
    public AlunoEspecial() {
        super();
    }

    public AlunoEspecial(String nome, Integer matricula, String curso, Boolean especial, Boolean cursoTrancado, Boolean semestreTrancado) {
        super(nome, matricula, curso, true, cursoTrancado, semestreTrancado);
    }
}
