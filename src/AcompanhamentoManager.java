import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcompanhamentoManager {

    private Menu menu;
    private AlunoManager alunoManager;
    private DisciplinaManager disciplinaManager;
    private List<Acompanhamento> listaAcompanhamentos;
    private File arquivo;
    private String nomeArquivo;

    public AcompanhamentoManager() {
        alunoManager = new AlunoManager();
        disciplinaManager = new DisciplinaManager();
        listaAcompanhamentos = new ArrayList<>();
        this.nomeArquivo = "data/acompanhamento.csv";
        this.arquivo = new File(nomeArquivo);
    }

    public AcompanhamentoManager(AlunoManager alunoManager, DisciplinaManager disciplinaManager ,Menu menu) {
        this.alunoManager = alunoManager;
        this.disciplinaManager = disciplinaManager; 
        this.menu = menu;
        this.nomeArquivo = "data/acompanhamento.csv";
        this.arquivo = new File(nomeArquivo);
        this.listaAcompanhamentos = new ArrayList<>();
        carregarDados();
    }

    public List<Acompanhamento> getListaAcompanhamentos() {
        return listaAcompanhamentos;
    }

    public void lancarNota() {
        try (Scanner sc = new Scanner(System.in)) {

            Aluno aluno = alunoManager.retornaAluno();

            System.out.println("\nDigite o código da disciplina que deseja lançar a nota:\n");
            int codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplina = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);
            Turma turmaEncontrada = null;
            for (Turma turma : disciplina.getTurmas()) {
                if (turma.getListaAlunos().contains(aluno)) {
                    turmaEncontrada = turma;
                    break;
                }
            }

            System.out.println("\nAluno: " + aluno.getNome() + " | Disciplina: " + disciplina.getNome() + " | Turma: " + turmaEncontrada.getNumeroDaTurma() + " | Avaliação: " + turmaEncontrada.getAvaliacao() + "\n\nDigite ENTER para continuar\n");
            sc.nextLine();

            System.out.println("\nDigite a nota da P1\n");
            double notaP1 = sc.nextDouble();
            sc.nextLine();
            System.out.println("\nDigite a nota da P2\n");
            double notaP2 = sc.nextDouble();
            sc.nextLine();
            System.out.println("\nDigite a nota da P3\n");
            double notaP3 = sc.nextDouble();
            sc.nextLine();
            System.out.println("\nDigite a nota do Seminário\n");
            double seminario = sc.nextDouble();
            sc.nextLine();
            System.out.println("\nDigite a nota da Lista de Exercícios do aluno\n");
            double listaDeExercicios = sc.nextDouble();
            sc.nextLine();
            System.out.println("\nDigite a presenca do aluno em porcentagem. Ex: 75\n");
            int presenca = sc.nextInt();
            sc.nextLine();

            double mediaFinal = calcularMediaFinal(turmaEncontrada, notaP1, notaP2, notaP3, seminario, listaDeExercicios);
            boolean aprovacao = aprovacao(presenca, mediaFinal);
            Acompanhamento statusDoAluno = new Acompanhamento(aluno, aluno.getMatricula(), disciplina.getNome(), turmaEncontrada, notaP1, notaP2, notaP3, seminario, listaDeExercicios, presenca, mediaFinal, aprovacao);
            listaAcompanhamentos.add(statusDoAluno);

            System.out.println("\nNota lançada do aluno " + aluno.getNome() + " lançada com sucesso\n");
            menu.menuAcompanhamento();
        }
    }

    private double calcularMediaFinal(Turma turma, double notaP1, double notaP2, double notaP3, double seminario, double listaDeExercicios) {
        double mediaFinal = 0;
        if (turma.getAvaliacao().equalsIgnoreCase("aritimetica")) {
            mediaFinal = (notaP1 + notaP2 + notaP3 + seminario + listaDeExercicios) / 5;
        } else if (turma.getAvaliacao().equalsIgnoreCase("ponderada")) {
            mediaFinal = (notaP1 + notaP2 * 2 + notaP3 * 3 + seminario + listaDeExercicios) / 8;
        }

        return Math.round(mediaFinal * 100.0)/100.0;
    }

    private boolean aprovacao(int presenca, double mediaFinal) {
        boolean aprovado = false;

        if (presenca >= 75 && mediaFinal >= 5) {
            aprovado = true;
        }
        return aprovado;
    }

    public void salvarDados(List<Acompanhamento> acompanhamentos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Aluno,Matrícula,Disciplina,Turma,Nota P1,Nota P2,Nota P3,Seminário,Lista de Exercícios,Presenca(%),Média Final,Aprovado");
            writer.newLine();

            for (Acompanhamento acompanhamento : acompanhamentos) {
                writer.write(acompanhamento.getAluno().getNome() + ","
                        + acompanhamento.getAluno().getMatricula() + ","
                        + acompanhamento.getDisciplina().getNome() + ","
                        + acompanhamento.getTurma().getNumeroDaTurma() + ","
                        + acompanhamento.getNotaP1() + ","
                        + acompanhamento.getNotaP2() + ","
                        + acompanhamento.getNotaP3() + ","
                        + acompanhamento.getSeminario() + ","
                        + acompanhamento.getListaDeExercicios() + ","
                        + acompanhamento.getPresenca() + ","
                        + acompanhamento.getMediaFinal() + ","
                        + acompanhamento.getAprovacao());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    private void carregarDados() {
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 12) {
                    int matricula = Integer.parseInt(dados[1].trim());
                    String nomeDisciplina = dados[2].trim();
                    int numeroDaTurma = Integer.parseInt(dados[3].trim());
                    double notaP1 = Double.parseDouble(dados[4].trim());
                    double notaP2 = Double.parseDouble(dados[5].trim());
                    double notaP3 = Double.parseDouble(dados[6].trim());
                    double seminario = Double.parseDouble(dados[7].trim());
                    double listaDeExercicios = Double.parseDouble(dados[8].trim());
                    int presenca = Integer.parseInt(dados[9].trim());
                    double mediaFinal = Double.parseDouble(dados[10]);
                    boolean aprovacao = Boolean.parseBoolean(dados[11]);

                    Disciplina disciplina = disciplinaManager.getListaDisciplinas().stream().filter(d -> d.getNome().equalsIgnoreCase(nomeDisciplina)).findFirst().orElse(null);
                    Aluno aluno = alunoManager.buscarAlunoPorMatricula(matricula);
                    Turma turma = null;

                    if (disciplina != null) {
                        for (Turma t : disciplina.getTurmas()) {
                            if (t.getNumeroDaTurma() == numeroDaTurma) {
                                turma = t;
                                break;
                            }
                        }
                    }

                    if (aluno != null && disciplina != null && turma != null) {
                        Acompanhamento acompanhamento = new Acompanhamento(aluno, matricula,disciplina.getNome(), turma, notaP1, notaP2, notaP3, seminario, listaDeExercicios, presenca, mediaFinal, aprovacao);
                        listaAcompanhamentos.add(acompanhamento);
                    }
                    
                }

            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar as disciplinas: " + e.getMessage());
        }

    }
}
