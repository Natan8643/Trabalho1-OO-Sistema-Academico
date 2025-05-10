
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoManager {

    private List<Aluno> alunos = new ArrayList<>();
    private Menu menu;
    private final String nomeArquivo = "data/alunos.csv";
    private final File arquivo = new File(nomeArquivo);

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void cadastrarAluno() {
        System.out.println();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do aluno:");
            String nome = sc.nextLine();
            System.out.println("\nDigite a matrícula:");
            Integer matricula = sc.nextInt();
            sc.nextLine();
            verificarMatricula(matricula);
            System.out.println("\nDigite o curso:");
            String curso = sc.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso, false, false);
            alunos.add(aluno);

            salvarAluno(aluno);
            menu.menuAluno();
        }
    }

    private void salvarAluno(Aluno aluno) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            if (arquivo.length() == 0) {
                writer.write("Nome,Matrícula,Curso,Semestre trancado,Curso trancado");
                writer.newLine();
            }
            writer.write(aluno.getNome() + ","
                    + aluno.getMatricula() + ","
                    + aluno.getCurso() + ","
                    + aluno.getSemestreTrancado() + ","
                    + aluno.getCursoTrancado());
            writer.newLine();

            System.out.println("\nAluno cadastrado e salvo no arquivo: " + nomeArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o aluno no arquivo: " + e.getMessage());
        }
    }

    public void editarAluno() {

    }

    public void listarAlunos() {
        if (!arquivo.exists()) {
            System.out.println("Nenhum aluno cadastrado ainda.");
            menu.menuAluno();
        }

        System.out.println("\nLista de Alunos Cadastrados:\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        menu.menuAluno();
    }

    private void verificarMatricula(int matricula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if(dados.length > 1) {
                    try {
                        int matriculaExistente = Integer.parseInt(dados[1].trim());
                        if (matriculaExistente == matricula) {
                        System.out.println("\nEssa matrícula já existe\n");
                        menu.menuAluno();
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar a matrícula " + e.getMessage());
        }
    }
}
