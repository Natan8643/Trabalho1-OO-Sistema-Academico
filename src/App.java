
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n---------------------------------------");
        System.out.println("Olá usuário! O que gostaria de fazer?");
        System.out.println("---------------------------------------");
        System.out.println("Digite o número de acordo com sua escolha:\n");
        System.out.println("1 - Modo Aluno\n2 - Modo Disciplina\n3 - Modo Acompanhamento\n");

        Scanner sc = new Scanner(System.in);
        int opçao1 = sc.nextInt();
        System.out.println(opçao1);

    }
}
