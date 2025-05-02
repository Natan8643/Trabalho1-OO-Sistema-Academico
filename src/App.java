
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
                System.out.println("1 - Modo Aluno\n2 - Modo Disciplina\n3 - Modo Acompanhamento\n");
                opçao = sc.nextInt();
                System.out.print("\n");
                switch (opçao) {
                    case 1:
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Aluno.\n---------------------------------------\n");
                        break;
                    case 2:
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Disciplina.\n---------------------------------------\n");
                        break;
                    case 3:
                        System.out.println("---------------------------------------\nVocê escolheu a opção Modo Acompanhamento.\n---------------------------------------\n");
                        break;
                    default:
                        System.out.println("---------------------------------------\nEssa opçãp não existe! Tente novamente.\n---------------------------------------\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("---------------------------------------\nEntrada inválida! Por favor, digite um número.\n---------------------------------------\n");
                sc.nextLine();
            }

        } while (opçao < 1 || opçao > 3);

    }
}
