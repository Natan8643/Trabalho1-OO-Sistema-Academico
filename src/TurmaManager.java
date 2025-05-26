import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurmaManager {

    private DisciplinaManager disciplinaManager;
    private Menu menu;
    private String nomeArquivo;
    private File arquivo;
    private List<Turma> listaDeTurmas = new ArrayList<>();
    private AlunoManager alunoManager;

    public TurmaManager() {
        this.listaDeTurmas = new ArrayList<>();
        this.nomeArquivo = "data/turma.csv";
        this.arquivo = new File(nomeArquivo);
        this.disciplinaManager = null;
        this.menu = null;
        this.alunoManager = null;
    }

    public TurmaManager(DisciplinaManager disciplinaManager, AlunoManager alunoManager, Menu menu) {
        this.disciplinaManager = disciplinaManager;
        this.alunoManager = alunoManager;
        this.menu = menu;
        this.nomeArquivo = "data/turma.csv";
        this.arquivo = new File(nomeArquivo);
        carregarDados();
    }

    public List<Turma> getListaDeTurmas() {
        return listaDeTurmas;
    }

    public void cadastrarTurma() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja cadastrar a turma\n");
            Integer codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplinaDaTurma = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);
            if (disciplinaDaTurma == null) {
                System.out.println("\nEssa disciplina não existe\n");
                menu.menuDisciplina();
            }
            System.out.println("\nDigite o número da turma\n");
            int numeroDaTurma = sc.nextInt();
            sc.nextLine();
            for (Turma turma : listaDeTurmas) {
                if (turma.getNumeroDaTurma().equals(numeroDaTurma)) {
                    System.out.println("\nUma turma já possui esse número\n");
                    menu.menuDisciplina();
                }
            }
            System.out.println("\nDigite o nome do professor\n");
            String professor = sc.nextLine();
            System.out.println("\nDigite o semestre. Ex: 25.1\n");
            String semestre = sc.nextLine();
            System.out.println("\nO modo de avaliação será:\n\n1 - Média aritimética\n2 - Média ponderada\n\nDigite o número correspondente (1 ou 2)\n");
            int escolha;
            String avaliacao = "";
            do {
                escolha = sc.nextInt();

                if (escolha == 1) {
                    avaliacao = "ponderada";
                    break;
                } else if (escolha == 2) {
                    avaliacao = "aritimetica";
                    break;
                }

                System.out.println("Entrada inválida. Por favor, digite 1 ou 2");

            } while (escolha != 1 && escolha != 2);

            System.out.println("\nO tipo da aula será:\n\n1 - Presencial\n2 - Remota\n\nDigite o número correspondente (1 ou 2)\n");
            String tipoDeAula = "";

            do {
                escolha = sc.nextInt();

                if (escolha == 1) {
                    tipoDeAula = "presencial";
                    break;
                } else if (escolha == 2) {
                    tipoDeAula = "remota";
                    break;
                }
            } while (escolha != 1 && escolha != 2);

            Integer sala = null;

            if (tipoDeAula.equals("presencial")) {
                System.out.println("\nDigite o número da sala:\n");
                sala = sc.nextInt();

            }

            System.out.println("\nDigite o horário em horas. Ex: 10\n");
            int horario = sc.nextInt();

            for (Turma turma : listaDeTurmas) {
                if (turma.getSala().equals(sala)) {
                    if (turma.getHorario().equals(horario)) {
                        System.out.println("\nA sala " + sala + " já está ocupada no nesse horário: " + horario);
                        menu.menuDisciplina();
                    }
                }
            }

            System.out.println("\nDigite a capacidade máxima:\n");
            int capacidadeMax = sc.nextInt();
            sc.nextLine();

            Turma novaTurma = new Turma(professor, semestre, avaliacao, tipoDeAula, sala, horario, capacidadeMax, disciplinaDaTurma, numeroDaTurma);
            disciplinaDaTurma.getTurmas().add(novaTurma);
            listaDeTurmas.add(novaTurma);

            System.out.println("\nTurma de " + disciplinaDaTurma.getNome() + " cadastrada com sucesso!\n");
            menu.menuDisciplina();

        }
    }

    public void listarTurmas() {

    }

    public void salvarDados(List<Turma> listaTurmas) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Disciplina,Semestre,Turma,Professor,Sala,Horário,Tipo de Aula,Avaliação,Capacidade Máxima,Número de vagas,Alunos");
            writer.newLine();

            for (Turma turma : listaTurmas) {
                StringBuilder alunosStr = new StringBuilder();

                for (Aluno aluno : turma.getListaAlunos()) {
                    alunosStr.append(aluno.getNome()).append(";");
                }
                if (alunosStr.length() > 0) {
                    alunosStr.setLength(alunosStr.length() - 1);
                }

                writer.write(turma.getDisciplina().getNome() + ","
                        + turma.getSemestre() + ","
                        + turma.getNumeroDaTurma() + ","
                        + turma.getProfessor() + ","
                        + turma.getSala() + ","
                        + turma.getHorario() + ","
                        + turma.getTipoDeAula() + ","
                        + turma.getAvaliacao() + ","
                        + turma.getCapacidadeMax() + ","
                        + turma.getVagas() + ","
                        + alunosStr.toString()
                );
                writer.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }

    }

    private void carregarDados() {
        if (!arquivo.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(arquivo)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Pula o cabeçalho
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(",", -1);

                // Ajuste os índices conforme a ordem dos campos no CSV
                String nomeDisciplina = campos[0];
                String semestre = campos[1];
                int numeroDaTurma = Integer.parseInt(campos[2]); // ajuste se você salvar esse campo
                String professor = campos[3];
                int sala = Integer.parseInt(campos[4]);
                int horario = Integer.parseInt(campos[5]);
                String tipoDeAula = campos[6];
                String avaliacao = campos[7];
                int capacidadeMax = Integer.parseInt(campos[8]);
                int vagas = Integer.parseInt(campos[9]);
                String alunosStr = campos.length > 10 ? campos[10] : "";

                // Busca a disciplina pelo nome (ou código, se preferir)
                Disciplina disciplina = disciplinaManager.getListaDisciplinas().stream()
                        .filter(d -> d.getNome().equalsIgnoreCase(nomeDisciplina))
                        .findFirst().orElse(null);

                if (disciplina == null) {
                    continue;
                }

                Turma turma = new Turma(professor, semestre, avaliacao, tipoDeAula, sala, horario, capacidadeMax, disciplina, numeroDaTurma);

                // Adiciona alunos à turma, se houver
                if (!alunosStr.isEmpty()) {
                    String[] nomesAlunos = alunosStr.split(";");
                    for (String nomeAluno : nomesAlunos) {
                        Aluno aluno = alunoManager.buscarAlunoPorNome(nomeAluno.trim());
                        if (aluno != null) {
                            turma.getListaAlunos().add(aluno);
                        }
                    }
                }
                turma.setVagas(turma.getCapacidadeMax() - turma.getListaAlunos().size());
                disciplina.getTurmas().add(turma);
                listaDeTurmas.add(turma);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar os dados do arquivo: " + e.getMessage());
        }
    }

}
