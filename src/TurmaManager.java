
import java.util.Scanner;

public class TurmaManager {

    private DisciplinaManager disciplinaManager;
    private Menu menu;

    public void cadastrarTurma() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja cadastrar a turma\n");
            int codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplinaDaTurma = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);
            if (disciplinaDaTurma == null) {
                System.out.println("\nEssa disciplina não existe\n");
                menu.menuDisciplina();
            }

            System.out.println("\nDigite o nome do professor\n");
            String professor = sc.nextLine();
            System.out.println("\nDigite o semestre. Ex: 25.1\n");
            String semestre = sc.nextLine();
            System.out.println("\nO modo de avaliação será:\n1 - Média aritimética\n2 - Média ponderada\nDigite o número correspondente (1 ou 2)\n");
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

            System.out.println("\nO tipo da aula será:\n1 - Presencial\n2 - Remota\nDigite o número correspondente (1 ou 2)\n");
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

            System.out.println("\nDigite o número da sala:\n");
            int sala = sc.nextInt();
            sc.nextLine();

            System.out.println("\nDigite o horário em horas. Ex: 10");
            int horario = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite a capacidade máxima:");
            int capacidadeMax = sc.nextInt();
            sc.nextLine();

            Turma novaTurma = new Turma(professor, semestre, avaliacao, tipoDeAula, sala, horario, capacidadeMax);
            disciplinaDaTurma.getTurmas().add(novaTurma);

            System.out.println("\nTurma de " + disciplinaDaTurma.getNome() + " cadastrada com sucesso!\n");

        }
    }
}
