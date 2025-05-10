
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoManager {

    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do aluno:");
            String nome = sc.nextLine();
            System.out.println("Digite a matrícula:");
            Integer matricula = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o curso:");
            String curso = sc.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso, false, false);
            alunos.add(aluno);

            salvarAluno(aluno);
        }
    }

    private void salvarAluno(Aluno aluno) {
        String nomeArquivo = "data/alunos.csv";
        File arquivo = new File(nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            if (arquivo.length() == 0) {
                writer.write("Nome,Matrícula,Curso,Semestre trancado,Curso trancado");
            }
            writer.write(aluno.getNome() + ","
                    + aluno.getMatricula() + ","
                    + aluno.getCurso() + ","
                    + aluno.getSemetreTrancado() + ","
                    + aluno.getCursoTrancado());
                    writer.newLine();

                    System.out.println("Aluno salvo no arquivo: " + nomeArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o aluno no arquivo: " + e.getMessage());
        }
    }
}
