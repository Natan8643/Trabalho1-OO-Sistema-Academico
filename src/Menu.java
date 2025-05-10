
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Aluno aluno = new Aluno();
    private AlunoManager alunoManager;

    public Menu() {
        this.alunoManager = new AlunoManager();
        this.alunoManager.setMenu(this);
    }
    
    public void encerrarPrograma() {
        System.out.println("\n---------------------------------------\nEncerrando o programa!\n---------------------------------------\n");
        System.exit(0);
    }

    public void menuInicial() {
        System.out.println("\n---------------------------------------\nVocê está no Menu Inicial.\n---------------------------------------\n");
        try (Scanner sc = new Scanner(System.in)) {
            int opçao = 0;
            do {
                try {
                    System.out.println("Digite o número de acordo com sua escolha:\n");
                    System.out.println("1 - Modo Aluno\n2 - Modo Disciplina\n3 - Modo Acompanhamento\n4 - Fechar programa\n");
                    opçao = sc.nextInt();
                    System.out.print("\n");
                    switch (opçao) {
                        case 1:
                            menuAluno();
                            break;
                        case 2:
                            System.out.println("---------------------------------------\nVocê escolheu a opção Modo Disciplina.\n---------------------------------------\n");
                            break;
                        case 3:
                            System.out.println("---------------------------------------\nVocê escolheu a opção Modo Acompanhamento.\n---------------------------------------\n");
                            break;
                        case 4:
                            encerrarPrograma();
                        default:
                            System.out.println("---------------------------------------\nEssa opçãp não existe! Tente novamente.\n---------------------------------------\n");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                    sc.nextLine();
                }

            } while (opçao < 1 || opçao > 4);
        }
    }

    public void menuAluno() {
        System.out.println("---------------------------------------\nVocê está no Menu Aluno.\n---------------------------------------\n");
        try (Scanner sc = new Scanner(System.in)) {
            int opçao = 0;
            do {
                try {
                    System.out.println("O que gostaria de fazer?\n\nDigite o número de acordo com sua escolha:\n\n1 - Cadastrar/Editar um aluno\n2 - Editar aluno\n3 - Listar alunos\n4 - Matricular aluno em alguma disciplina\n5 - Trancar disciplina/semestre/curso\n6 - Voltar para o Menu Inicial\n7 - Encerrar o programa\n");
                    opçao = sc.nextInt();
                    System.out.print("\n");

                    switch (opçao) {
                        case 1 -> {
                            //chamar funçao cadastrar/editar aluno
                            alunoManager.cadastrarAluno();
                        }
                        case 2 -> {
                            //chamar função editar aluno
                        }
                        case 3 -> {
                            //chamar funçao listar aluno
                        }
                        case 4 -> {
                            //chamar funçao matricular na disciplina
                        }
                        case 5 -> {
                            // chamar funçao trancamento
                        }
                        case 6 ->
                            menuInicial();
                        case 7 ->
                            encerrarPrograma();
                        default -> {
                            System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                            sc.nextLine();
                        }
                    }

                } catch (InputMismatchException e) {
                }

            } while (opçao < 1 || opçao > 7);

        }
    }

    public static void alunoEspecial() {

    }
}
