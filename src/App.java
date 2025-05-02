
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("\n---------------------------------------");
        System.out.println("Olá usuário! O que gostaria de fazer?");
        System.out.println("---------------------------------------");
        menuInicial();
    }

    public static void menuInicial() {

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
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Aluno.\n---------------------------------------\n");
                        menuAluno();
                        break;
                    case 2:
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Disciplina.\n---------------------------------------\n");
                        break;
                    case 3:
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Acompanhamento.\n---------------------------------------\n");
                        break;
                    case 4:
                        System.out.println("---------------------------------------\nEncerrando o programa!\n---------------------------------------\n");
                        System.exit(0);
                    default:
                        System.out.println("---------------------------------------\nEssa opçãp não existe! Tente novamente.\n---------------------------------------\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                sc.nextLine();
            }

        } while (opçao < 1 || opçao > 3);

        sc.close();
    }

    public static void menuAluno() {
        Scanner sc = new Scanner(System.in);
        String resposta;

        do {
            System.out.println("Você deseja fazer alguma operação com algum aluno especial?\n\nDigite \"Sim\" ou \"Nao\" (sem acento).\n");
            resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                //chamar a função aluno especial
                break;
            } 
            
            else if (resposta.equalsIgnoreCase("Nao")) {
                break;
            } 
            
            else {
                System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite uma das opções.\n---------------------------------------\n");
            }

        } while (true);

       
    }

}
