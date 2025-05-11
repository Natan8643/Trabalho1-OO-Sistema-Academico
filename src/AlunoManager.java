
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

    public List<Aluno> getListaAlunos() {
        return alunos;
    }

    public AlunoManager() {
        carregarAlunos(); // Carrega os dados automaticamente ao criar o objeto
    }

    public void cadastrarAluno() {
        System.out.println();
        try (Scanner sc = new Scanner(System.in,"UTF-8")) {
            System.out.println("Digite o nome do aluno:");
            String nome = sc.nextLine();
            System.out.println("\nDigite a matrícula:");
            Integer matricula = sc.nextInt();
            sc.nextLine();
            if (verificarMatricula(matricula)) {
                System.out.println("\nEssa matrícula já existe.\n");
                menu.menuAluno();
            }
            System.out.println("\nDigite o curso:");
            String curso = sc.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso, false, false);
            alunos.add(aluno);

            menu.menuAluno();
        }
    }


    public void editarAluno() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite a matrícula do aluno que deseja editar:");
            Integer matricula = sc.nextInt();
            sc.nextLine();

            Aluno alunoParaEditar = buscarAlunoPorMatricula(matricula);
            
            if (alunoParaEditar != null) {
                System.out.println("Digite o novo nome do aluno:");
                alunoParaEditar.setNome(sc.nextLine());
                System.out.println("Digite a matrícula do aluno:");
                alunoParaEditar.setMatricula(sc.nextInt());
                sc.nextLine();
                System.out.println("Digite o novo curso do aluno:");
                alunoParaEditar.setCurso(sc.nextLine());

                salvarDados(alunos); // Salva os dados atualizados
                System.out.println("\nAluno editado com sucesso!\n");
                menu.menuAluno();
            } 
                System.out.println("Matrícula não encontrada.");
                menu.menuAluno();
        }
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado ainda.");
            menu.menuAluno();
        }

        System.out.println("\nLista de Alunos Cadastrados:\n");
        System.out.println("Nome,Matrícula,Curso,Semestre trancado,Curso trancado\n"); // Cabeçalho

        for (Aluno aluno : alunos) {
            System.out.println(aluno.getNome() + ","
                    + aluno.getMatricula() + ","
                    + aluno.getCurso() + ","
                    + aluno.getSemestreTrancado() + ","
                    + aluno.getCursoTrancado() + "\n");
        }
        menu.menuAluno();
    }

    private boolean verificarMatricula(int matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    public void salvarDados(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            // Escreve o cabeçalho no arquivo
            writer.write("Nome,Matrícula,Curso,Semestre trancado,Curso trancado");
            writer.newLine();

            // Escreve os dados de cada aluno
            for (Aluno aluno : alunos) {
                writer.write(aluno.getNome() + ","
                        + aluno.getMatricula() + ","
                        + aluno.getCurso() + ","
                        + aluno.getSemestreTrancado() + ","
                        + aluno.getCursoTrancado());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    private void carregarAlunos() {
        if (!arquivo.exists()) {
            return; // Se o arquivo não existir, não há alunos para carregar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    String nome = dados[0].trim();
                    int matricula = Integer.parseInt(dados[1].trim());
                    String curso = dados[2].trim();
                    boolean semestreTrancado = Boolean.parseBoolean(dados[3].trim());
                    boolean cursoTrancado = Boolean.parseBoolean(dados[4].trim());

                    Aluno aluno = new Aluno(nome, matricula, curso, semestreTrancado, cursoTrancado);
                    alunos.add(aluno); // Adiciona o aluno à lista
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os alunos: " + e.getMessage());
        }
    }

    private void trancamento() {
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Digite a matrícula do aluno que deseja realizar a operação");
            int matricula =  sc.nextInt();
            sc.nextLine();

            buscarAlunoPorMatricula(matricula);

            System.out.println("\nVocê deseja:\n\n1 - Trancar disciplina\n2 - Trancar semestre\n3 - Trancar curso\n4 - Voltar para o Menu Aluno\n");

            int opçao = sc.nextInt();
            sc.nextLine();
            switch (opçao) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {
                    menu.menuAluno();
                }
            }

        }
    }
    private Aluno buscarAlunoPorMatricula(int matricula) {
    for (Aluno aluno : alunos) {
        if (aluno.getMatricula() == matricula) {
            return aluno; // Retorna o aluno encontrado
        }
    }
    return null; // Retorna null se o aluno não for encontrado
}
}
