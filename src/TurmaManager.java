
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurmaManager {

    private DisciplinaManager disciplinaManager;
    private Menu menu;
    private final String nomeArquivo = "data/turma.csv";
    private final File arquivo = new File(nomeArquivo);
    private List<Turma> listaDeTurmas = new ArrayList<>();

    public TurmaManager(DisciplinaManager disciplinaManager) {
        this.disciplinaManager = disciplinaManager;
    }

    public List<Turma> getListaDeTurmas() {
        return listaDeTurmas;
    }

    public void cadastrarTurma() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja cadastrar a turma\n");
            Integer codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplinaDaTurma = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);
            if (disciplinaDaTurma == null) {
                System.out.println("\nEssa disciplina não existe\n");
                menu.menuDisciplina();
            }
            System.out.println("\nDigite o número da turma\n");
            int numeroDaTurma = sc.nextInt();
            sc.nextLine();
            System.out.println("\nDigite o nome do professor\n");
            String professor = sc.nextLine();
            System.out.println("\nDigite o semestre. Ex: 25.1\n");
            String semestre = sc.nextLine();
            System.out.println("\nO modo de avaliação será:\n\n1 - Média aritimética\n2 - Média ponderada\n\nDigite o número correspondente (1 ou 2)\n");
            int escolha;
            String avaliacao = "";
            do {
                escolha = sc.nextInt();
                sc.nextLine();

                if (escolha == 1) {
                    avaliacao = "ponderada";
                    break;
                } else if (escolha == 2) {
                    avaliacao = "aritimetica";
                    break;
                }

                System.out.println("Entrada inválida. Por favor, digite 1 ou 2");

            } while (escolha != 1 || escolha != 2);

            System.out.println("\nO tipo da aula será:\n\n1 - Presencial\n2 - Remota\n\nDigite o número correspondente (1 ou 2)\n");
            String tipoDeAula = "";

            do {
                escolha = sc.nextInt();
                sc.nextLine();

                if (escolha == 1) {
                    tipoDeAula = "presencial";
                    break;
                } else if (escolha == 2) {
                    tipoDeAula = "remota";
                    break;
                }
            } while (escolha != 1 || escolha != 2);

            Integer sala = null;

            if (tipoDeAula.equals("presencial")) {
                System.out.println("\nDigite o número da sala:\n");
                sala = sc.nextInt();
                sc.nextLine();
            }

            System.out.println("\nDigite o horário em horas. Ex: 10\n");
            int horario = sc.nextInt();
            sc.nextLine();

            System.out.println("\nDigite a capacidade máxima:\n");
            int capacidadeMax = sc.nextInt();
            sc.nextLine();

            Turma novaTurma = new Turma(professor, semestre, avaliacao, tipoDeAula, sala, horario, capacidadeMax, disciplinaDaTurma,numeroDaTurma);
            disciplinaDaTurma.getTurmas().add(novaTurma);
            listaDeTurmas.add(novaTurma);

            System.out.println("\nTurma de " + disciplinaDaTurma.getNome() + " cadastrada com sucesso!\n");

            menu.menuDisciplina();

        }
    }

    public void listarTurmas() {
        
    }

    // public void salvarDados(List<Turma> listaTurmas) {

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
    //         writer.write("Disciplina,Semestre,Professor,Sala,Horário,Tipo de Aula,Avaliação,Capacidade Máxima,Alunos");
    //         writer.newLine();

    //         for (Turma turma : listaTurmas) {
    //             StringBuilder alunosStr = new StringBuilder();

    //             for (Aluno aluno : turma.getListaAlunos()) {
    //                 alunosStr.append(aluno.getNome()).append(",");
    //             }
    //             if (alunosStr.length() > 0) {
    //                 alunosStr.setLength(alunosStr.length() - 1);
    //             }

    //             writer.write(turma.getDisciplina().getNome() + ","
    //                     + turma.getSemestre() + ","
    //                     + turma.getProfessor()
    //                     + turma.getSala() + ","
    //                     + turma.getHorario() + ","
    //                     + turma.getTipoDeAula() + ","
    //                     + turma.getAvaliacao() + ","
    //                     + turma.getCapacidadeMax() + ","
    //                     + alunosStr.toString()
    //             );
    //             writer.newLine();
    //         }

    //     } catch (Exception e) {
    //         System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
    //     }

    // }

}
