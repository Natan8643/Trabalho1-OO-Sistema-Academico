import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DisciplinaManager {

    private List<Disciplina> listaDisciplinas;
    private final String nomeArquivo;
    private final File arquivo;
    private Menu menu;

    public DisciplinaManager() {
        this.listaDisciplinas = new ArrayList<>();
        this.nomeArquivo = "data/disciplina.csv";
        this.arquivo = new File(nomeArquivo);
        this.menu = null;
    }

    public DisciplinaManager(Menu menu) {
        this.listaDisciplinas = new ArrayList<>();
        this.nomeArquivo = "data/disciplina.csv";
        this.arquivo = new File(nomeArquivo);
        this.menu = menu;
        carregarDados();
    }

    public List<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public void cadastrarDisciplina() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o nome da disciplina:\n");
            String nome = sc.nextLine();

            Integer codigo = null;
            while (codigo == null) {
                try {
                    System.out.println("\nDigite o código:\n");
                    codigo = sc.nextInt();
                    sc.nextLine();

                    if (buscarDisiciplinaPorCodigo(codigo) != null) {
                        System.out.println("---------------------------------------");
                        System.out.println("Já existe uma disciplina com esse código!");
                        System.out.println("---------------------------------------");
                        codigo = null;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------");
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                    System.out.println("---------------------------------------");
                    sc.nextLine();
                }
            }

            Integer cargaHoraria = null;
            while (cargaHoraria == null) {
                try {
                    System.out.println("\nDigite a carga horária (apenas o número em horas)\n");
                    cargaHoraria = sc.nextInt();
                    sc.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------");
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                    System.out.println("---------------------------------------");
                    sc.nextLine();
                }
            }

            System.out.println("\nDigite o pré-requisito:\n");
            String preRequisito = sc.nextLine();

            Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisito);
            listaDisciplinas.add(disciplina);

            System.out.println("\nDisciplina cadastrada com sucesso!\n");
            menu.menuDisciplina();
        }
    }

    public void salvarDados(List<Disciplina> listaDisciplinas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Nome,Código,Carga Horária,PréRequisito");
            writer.newLine();

            for (Disciplina disciplina : listaDisciplinas) {
                writer.write(disciplina.getNome() + ","
                        + disciplina.getCodigo() + ","
                        + disciplina.getCargaHoraria() + ","
                        + disciplina.getPreRequisito());
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

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String nome = dados[0].trim();
                    int codigo = Integer.parseInt(dados[1].trim());
                    int cargaHoraria = Integer.parseInt(dados[2].trim());
                    String preRequisito = dados[3].trim();

                    Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisito);
                    listaDisciplinas.add(disciplina);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as disciplinas: " + e.getMessage());
        }
    }

    public void listarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            System.out.println("\nA lista de disciplinas está vazia\n");
            menu.menuDisciplina();
        }

        System.out.println("\nLista de disciplinas cadastradas\n");
        System.out.println("Nome,Código,Carga horária(h),Pré-requisito\n");

        for (Disciplina disciplina : listaDisciplinas) {
            System.out.println(disciplina.getNome() + ","
                    + disciplina.getCodigo() + ","
                    + disciplina.getCargaHoraria() + "h,"
                    + disciplina.getPreRequisito() + "\n");
        }

        System.out.println("\nClique ENTER para continuar\n");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        menu.menuDisciplina();
    }

    public Disciplina buscarDisiciplinaPorCodigo(Integer codigo) {
        for (Disciplina disciplina : listaDisciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public void listarTurmas() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o número da disciplina que deseja listar as turmas:\n");
            int codigo = sc.nextInt();
            sc.nextLine();

            Disciplina disciplina = buscarDisiciplinaPorCodigo(codigo);
            if (disciplina == null) {
                System.out.println("\nDisciplina não encontrada\n");
                menu.menuDisciplina();
            }
            
            List<Turma> turmas = disciplina.getTurmas();

            if (turmas.isEmpty()) {
                System.out.println("\nNenhuma turma cadastrada para essa disciplina\n");
                menu.menuAluno();
            }

            System.out.println("\nTurmas cadastradas na disciplina " + disciplina.getNome() + "\n");

            for (Turma turma : turmas) {
                System.out.println("- Turma: " + turma.getNumeroDaTurma() + " | - Professor: " + turma.getProfessor()
                        + " | Semestre: " + turma.getSemestre()
                        + " | Sala: " + turma.getSala()
                        + " | Horário: " + turma.getHorario()
                        + "h | Tipo de Aula: " + turma.getTipoDeAula()
                        + " | Capacidade Máxima: " + turma.getCapacidadeMax()
                        + " | Número de vagas: " + turma.getVagas() + "\n");

            }
            menu.menuDisciplina();
        }
    }

}
