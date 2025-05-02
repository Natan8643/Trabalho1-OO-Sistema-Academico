
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("\n---------------------------------------");
        System.out.println("Olá usuário!");
        System.out.println("---------------------------------------");
        menuInicial();
    }

    public static void encerrarPrograma() {
        System.out.println("\n---------------------------------------\nEncerrando o programa!\n---------------------------------------\n");
        System.exit(0);
    }

    public static void menuInicial() {
        System.out.println("\n---------------------------------------\nVocê está no Menu Inicial.\n---------------------------------------\n");
        Scanner sc = new Scanner(System.in);
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

        sc.close();
    }

    public static void menuAluno() {
        System.out.println("---------------------------------------\nVocê está no Menu Aluno.\n---------------------------------------\n");
        Scanner sc = new Scanner(System.in);
        String resposta;

        do {
            System.out.println("Você deseja fazer alguma operação com algum aluno especial?\n\nDigite \"Sim\" ou \"Nao\" (sem acento).\n");
            resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                //chamar a função aluno especial
                alunoEspecial();
                break;
            } else if (resposta.equalsIgnoreCase("Nao")) {
                //chamar a função aluno normal
                alunoNormal();
                break;
            } else {
                System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite uma das opções.\n---------------------------------------\n");
            }

        } while (true);

        sc.close();
    }

    public static void alunoNormal() {
        Scanner sc = new Scanner(System.in);
        int opçao = 0;

        System.out.println("\n---------------------------------------\nVocê está na aba Aluno Normal\n---------------------------------------\n");

        do {
            try {
                System.out.println("O que gostaria de fazer?\n\nDigite o número de acordo com sua escolha:\n\n1 - Cadastrar/Editar um aluno\n2 - Listar os alunos cadastrados\n3 - Matricular aluno\n4 - Trancar Disciplina/Semestre\n5 - Voltar para o Menu Aluno\n6 - Voltar para o Menu Inicial\n7 - Encerrar o programa\n");
                opçao = sc.nextInt();

                switch (opçao) {
                    case 1:
                        //chamar funçao cadastrar/editar
                        break;
                    case 2:
                        //chamar função listar/editar
                        break;
                    case 3:
                        //chamar funçao matricular
                        break;
                    case 4:
                        //chamar funçao trancar disciplina/semestre
                        break;
                    case 5:
                        menuAluno();
                        break;
                    case 6:
                        menuInicial();
                        break;
                    case 7:
                        encerrarPrograma();
                        break;
                    default:
                        System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                        sc.nextLine();
                }

            } catch (InputMismatchException e) {
            }

        } while (opçao < 1 || opçao > 7);

        sc.close();

    }

    public static void alunoEspecial() {

    }
}
