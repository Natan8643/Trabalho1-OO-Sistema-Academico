
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoManager {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(){
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do aluno:");
            String nome = sc.nextLine();
            System.out.println("Digite a matrícula:");
            Integer matricula = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o curso:");
            String curso = sc.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso, false, false);
            alunos.add(aluno);
            
            System.out.println("Aluno cadastrado com sucesso");
        }  
    }
}
