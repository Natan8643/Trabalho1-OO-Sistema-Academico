
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

    public AcompanhamentoManager(AlunoManager alunoManager, DisciplinaManager disciplinaManager, Menu menu) {
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

            if (turmaEncontrada == null) {
                System.out.println("\nO aluno não está matriculado em nenhuma turma dessa disciplina.");
                menu.menuAcompanhamento();
                return;
            }

            // Agora é seguro acessar turmaEncontrada.getNumeroDaTurma()
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
            Acompanhamento statusDoAluno = new Acompanhamento(aluno, disciplina, turmaEncontrada, notaP1, notaP2, notaP3, seminario, listaDeExercicios, presenca, mediaFinal, aprovacao);
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

        return Math.round(mediaFinal * 100.0) / 100.0;
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
                        Acompanhamento acompanhamento = new Acompanhamento(aluno, disciplina, turma, notaP1, notaP2, notaP3, seminario, listaDeExercicios, presenca, mediaFinal, aprovacao);
                        listaAcompanhamentos.add(acompanhamento);
                    }

                }

            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar as disciplinas: " + e.getMessage());
        }

    }

    public void statusDoAluno() {
        Aluno aluno = alunoManager.retornaAluno();

        System.out.println("\nTurmas do aluno " + aluno.getNome() + "\n");
        for (Acompanhamento acompanhamento : listaAcompanhamentos) {
            if (acompanhamento.getAluno().equals(aluno)) {
                Turma turma = acompanhamento.getTurma();
                Disciplina disciplina = acompanhamento.getDisciplina();
                System.out.print("\nDisciplina: " + disciplina.getNome() + " | Turma: " + turma.getNumeroDaTurma() + " | Média final: " + acompanhamento.getMediaFinal() + " | Presença " + acompanhamento.getPresenca() + "% | Aprovado: " + acompanhamento.getAprovacao());
            }
            if (acompanhamento.getAprovacao() == true) {
                System.out.println();
            }

            if (acompanhamento.getAprovacao() == false) {

                if (acompanhamento.getMediaFinal() < 5.0 && acompanhamento.getPresenca() < 75) {
                    System.out.println(" | Reprovado por média menor que 5 e por presença menor que 75%\n");
                } else if (acompanhamento.getMediaFinal() < 5.0 || acompanhamento.getPresenca() < 75) {

                    if (acompanhamento.getMediaFinal() < 5.0) {
                        System.out.println(" | Reprovado por média menor que 5\n");
                    } else if (acompanhamento.getPresenca() < 75) {
                        System.out.println(" | Reprovado por presença menor que 75%\n");
                    }
                }

            }
        }
        menu.menuAcompanhamento();
    }

    public void relatorioTurma() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja ver as turmas:\n");
            int codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplina = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);

            System.out.println("\nDeseja com ou sem os dados da turma?\n\nDigite\n1 - Com dados\n2 - Sem dados\n");
            int opcao = sc.nextInt();
            sc.nextLine();
            for (Turma turma : disciplina.getTurmas()) {
                int aprovados = 0;
                double totalAlunos = 0;
                double somaMedias = 0;

                for (Acompanhamento acompanhamento : listaAcompanhamentos) {
                    if (acompanhamento.getTurma().equals(turma)) {
                        somaMedias += acompanhamento.getMediaFinal();
                        totalAlunos++;
                        if (acompanhamento.getAprovacao()) {
                            aprovados++;
                        }
                    }
                }

                double mediaTurma = (totalAlunos > 0) ? Math.round((somaMedias / totalAlunos) * 100.0) / 100.0 : 0;

                if (opcao == 1) {
                    System.out.println("\nTurma: " + turma.getNumeroDaTurma() + " | Professor: "+ turma.getProfessor() + " | Tipo de aula: " + turma.getTipoDeAula() + " | Aprovados: " + aprovados + " | Média da turma: " + mediaTurma + "\n");
                }
                else if (opcao == 2) {
                    System.out.println("\nTurma: " + turma.getNumeroDaTurma() + " | Aprovados: " + aprovados + " | Média da turma: " + mediaTurma + "\n");
                }
            }

            menu.menuAcompanhamento();
        }
    }

    public void relatorioDisciplina() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja ver o relatório:\n");
            int codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplina = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);

            int aprovados = 0;
            double totalAlunos = 0;
            double somaMedias = 0;

            for (Acompanhamento acompanhamento : listaAcompanhamentos) {
                if (acompanhamento.getDisciplina().equals(disciplina)) {
                    somaMedias += acompanhamento.getMediaFinal();
                    totalAlunos++;
                    if (acompanhamento.getAprovacao()) {
                        aprovados++;
                    }
                }

            }

            double mediaDisciplina = (totalAlunos > 0) ? Math.round((somaMedias / totalAlunos) * 100.0) / 100.0 : 0;
            System.out.println("\nDisciplina: " + disciplina.getNome() + " | Aprovados: " + aprovados + " | Média da turma: " + mediaDisciplina + "\n");

            menu.menuAcompanhamento();
        }
    }

    public void relatorioProfessor() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o nome do professor que deseja ver o relatório\n");
            String professor = sc.nextLine();

            int aprovados = 0;
            double totalAlunos = 0;
            double somaMedias = 0;
            boolean professorEncontrado = false;

            for (Acompanhamento acompanhamento : listaAcompanhamentos) {
                if (acompanhamento.getTurma().getProfessor().equalsIgnoreCase(professor)) {
                    professorEncontrado = true;
                    somaMedias += acompanhamento.getMediaFinal();
                    totalAlunos++;
                    if (acompanhamento.getAprovacao()) {
                        aprovados++;
                    }

                }
            }
            if (!professorEncontrado) {
                System.out.println("\nProfessor não encontrado\n");
                menu.menuAcompanhamento();
            }

            double mediaDisciplina = (totalAlunos > 0) ? Math.round((somaMedias / totalAlunos) * 100.0) / 100.0 : 0;
            System.out.println("\nProfessor: " + professor + " | Aprovados: " + aprovados + " | Média da turma: " + mediaDisciplina + "\n");

            menu.menuAcompanhamento();
        }
    }
}
