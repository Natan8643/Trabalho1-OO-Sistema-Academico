
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private DisciplinaManager disciplinaManager;
    private final AlunoManager alunoManager;
    private final TurmaManager turmaManager;

    public Menu() {
        alunoManager = new AlunoManager(this);
        disciplinaManager = new DisciplinaManager(this);
        turmaManager = new TurmaManager(disciplinaManager,alunoManager, this);
    }

    public void encerrarPrograma() {
        System.out.println("\n---------------------------------------\nEncerrando o programa!\n---------------------------------------\n");
        alunoManager.salvarDados(alunoManager.getListaAlunos());
        disciplinaManager.salvarDados(disciplinaManager.getListaDisciplinas());
        turmaManager.salvarDados(turmaManager.getListaDeTurmas());
        System.exit(0);
    }

    public void menuInicial() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = 0;
            do {
                System.out.println("\n---------------------------------------\nVocê está no Menu Inicial.\n---------------------------------------\n");
                System.out.println("Digite o número de acordo com sua escolha:\n");
                System.out.println("1 - Modo Aluno\n2 - Modo Disciplina\n3 - Modo Acompanhamento\n4 - Fechar programa\n");
                try {
                    opcao = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\n");
                    switch (opcao) {
                        case 1:
                            menuAluno();
                            break;
                        case 2:
                            menuDisciplina();
                            break;
                        case 3:
                            System.out.println("---------------------------------------\nVocê escolheu a opção Modo Acompanhamento.\n---------------------------------------\n");
                            break;
                        case 4:
                            encerrarPrograma();
                        default:
                            System.out.println("---------------------------------------\nEssa opção não existe! Tente novamente.\n---------------------------------------\n");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                    sc.nextLine();
                }

            } while (opcao < 1 || opcao > 4);
        }
    }

    public void menuAluno() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = 0;
            do {
                System.out.println("---------------------------------------\nVocê está no Menu Aluno.\n---------------------------------------\n");
                System.out.println("O que gostaria de fazer?\n\nDigite o número de acordo com sua escolha:\n\n1 - Cadastrar um aluno\n2 - Editar aluno\n3 - Buscar aluno\n4 - Listar alunos\n5 - Matricular aluno em alguma discipçina\n6 - Trancar/Destrancar disciplina/semestre/curso\n7 - Remover aluno\n8 - Voltar para o menu inicial\n9 - Encerrar programa\n");
                try {
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1 -> {
                            //chamar funçao cadastrar/editar aluno
                            alunoManager.cadastrarAluno();
                        }
                        case 2 -> {
                            //chamar função editar aluno
                            alunoManager.editarAluno();
                        }
                        case 3 -> {
                            Aluno alunoAExibir = alunoManager.retornaAluno();
                            System.out.println("\n" + alunoAExibir.toString() + "\n");
                            menuAluno();
                        }
                        case 4 -> {
                            //chamar funçao listar aluno
                            alunoManager.listarAlunos();
                        }
                        case 5 -> {
                            //chamar funçao matricular na disciplina
                            alunoManager.cadastrarAlunoNaTurma();
                        }
                        case 6 ->
                            // chamar funçao trancamento
                            alunoManager.trancamento();
                        case 7 ->
                            alunoManager.removerAluno();
                        case 8 ->
                            menuInicial();
                        case 9 ->
                            encerrarPrograma();
                        default -> {
                            System.out.println("---------------------------------------\nEssa opção não existe! Tente novamente.\n---------------------------------------\n");
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                    sc.nextLine();
                }

            } while (opcao < 1 || opcao > 7);

        }
    }

    public void menuDisciplina() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao = 0;
            do {
                System.out.println("---------------------------------------\nVocê está no Menu Disciplina.\n---------------------------------------\n");
                System.out.println("O que gostaria de fazer?\n\nDigite o número de acordo com sua escolha:\n\n1 - Cadastrar uma disciplina\n2 - Listar disciplinas\n3 - Criar turmas\n4 - Listar turmas\n5 - Voltar para o menu inicial\n6 - Encerrar o programa\n");
                try {
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1 -> {
                            //chamar funçao cadastrar disciplina
                            disciplinaManager.cadastrarDisciplina();
                        }
                        case 2 -> {
                            disciplinaManager.listarDisciplinas();
                        }

                        case 3 -> {
                            turmaManager.cadastrarTurma();
                        }
                        case 4 -> {
                            //listar turmas
                        }
                        case 5 -> {
                            menuInicial();
                        }
                        case 6 -> {
                            encerrarPrograma();
                        }

                        default ->
                            System.out.println("---------------------------------------\nEssa opção não existe! Tente novamente.\n---------------------------------------\n");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                    sc.nextLine();
                }
            } while (opcao < 1 || opcao > 6);
        }

    }
}
