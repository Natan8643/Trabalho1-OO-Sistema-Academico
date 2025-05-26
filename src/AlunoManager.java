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

public class AlunoManager {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Aluno> alunoEsp = new ArrayList<>();
    private Menu menu;
    private final String nomeArquivo = "data/alunos.csv";
    private final File arquivo = new File(nomeArquivo);
    private final DisciplinaManager disciplinaManager;

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Aluno> getListaAlunos() {
        return alunos;
    }

    public AlunoManager(DisciplinaManager disciplinaManager) {
        carregarAlunos(); // Carrega os dados automaticamente ao criar o objeto
        this.disciplinaManager = disciplinaManager;
    }

    public void cadastrarAluno() {
        System.out.println();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o nome do aluno:\n");
            String nome = sc.nextLine();

            Integer matricula = null;
            while (matricula == null) {
                System.out.println("\nDigite a matrícula:\n");
                try {
                    matricula = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer
                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------");
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                    System.out.println("---------------------------------------");
                    sc.nextLine(); // Limpa o buffer para evitar loop infinito
                }
            }

            if (buscarAlunoPorMatricula(matricula) != null) {
                System.out.println("\nEssa matrícula já existe.\n");
                menu.menuAluno();
                return;
            }

            System.out.println("\nDigite o curso:\n");
            String curso = sc.nextLine();
            Boolean especial = false;

            System.out.println("\nO aluno é especial?\n\nDigite Sim ou Nao:\n");
            String resposta;
            do {
                resposta = sc.nextLine();

                if (resposta.equalsIgnoreCase("sim")) {
                    especial = true;
                    break;
                } else if (resposta.equalsIgnoreCase("nao")) {
                    especial = false;
                    break;
                }

                System.out.println("\nEntrada inválida. Por favor, digite Sim ou Nao\n");

            } while (!resposta.equalsIgnoreCase("sim") && !resposta.equalsIgnoreCase("nao"));

            Aluno aluno;
            if (especial) {
                aluno = new AlunoEspecial(nome, matricula, curso, especial, false, false);
            } else {
                aluno = new Aluno(nome, matricula, curso, especial, false, false);
            }
            alunos.add(aluno);

            if (especial) {
                AlunoEspecial alunoEspecial = new AlunoEspecial(nome, matricula, curso, especial, false, false);
                alunoEsp.add(alunoEspecial);
            }

            System.out.println("\nDigite as matérias que o aluno já concluiu: (Digite separadas por ENTER)\n\nCaso queria finalizar o cadastro, digite 1\n");
            String preRequisitos;

            do {
                preRequisitos = sc.nextLine();
                if (!preRequisitos.equals("1")) {
                    aluno.getPreRequisito().add(preRequisitos);
                }

            } while (!preRequisitos.equals("1"));

            System.out.println("\nAluno " + aluno.getNome() + " cadastrado\n");

            menu.menuAluno();
        }
    }

    public void editarAluno() {
        try (Scanner sc = new Scanner(System.in)) {
            Aluno alunoParaEditar = retornaAluno();

            if (alunoParaEditar != null) {
                System.out.println("Digite o novo nome do aluno:");
                alunoParaEditar.setNome(sc.nextLine());
                System.out.println("Digite a matrícula do aluno:");
                alunoParaEditar.setMatricula(sc.nextInt());
                sc.nextLine();
                System.out.println("Digite o novo curso do aluno:");
                alunoParaEditar.setCurso(sc.nextLine());
                System.out.println("\nO aluno é especial?\n");
                String resposta;
                do {
                    resposta = sc.nextLine();

                    if (resposta.equalsIgnoreCase("sim")) {
                        alunoParaEditar.setEspecial(true);
                        break;
                    } else if (resposta.equalsIgnoreCase("nao")) {
                        alunoParaEditar.setEspecial(false);
                        break;
                    }

                    System.out.println("\nEntrada inválida. Por favor, digite Sim ou Nao\n");

                } while (!resposta.equalsIgnoreCase("sim") && !resposta.equalsIgnoreCase("nao"));

                alunoParaEditar.getPreRequisito().clear();

                String preRequisitos;
                do {
                    preRequisitos = sc.nextLine();
                    if (!preRequisitos.equals("1")) {
                        alunoParaEditar.getPreRequisito().add(preRequisitos);
                    }

                } while (!preRequisitos.equals("1"));

                System.out.println("\nAluno " + alunoParaEditar.getNome() + " editado com sucesso!\n");
                menu.menuAluno();
            }
            System.out.println("Matrícula não encontrada.");
            menu.menuAluno();
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado ainda.");
            menu.menuAluno();
        }

        System.out.println("\nLista de Alunos Cadastrados:\n");
        System.out.println("Nome,Matrícula,Curso,Especial,Semestre trancado,Curso trancado,Pré-requisito\n"); // Cabeçalho

        for (Aluno aluno : alunos) {
            String preReqStr = "";
            if (aluno.getPreRequisito() != null && !aluno.getPreRequisito().isEmpty()) {
                preReqStr = String.join(";", aluno.getPreRequisito());
            }
            System.out.println(aluno.getNome() + ","
                    + aluno.getMatricula() + ","
                    + aluno.getCurso() + ","
                    + aluno.getEspecial() + ","
                    + aluno.getSemestreTrancado() + ","
                    + aluno.getCursoTrancado() + ","
                    + preReqStr + "\n");
        }
        System.out.println("\nClique ENTER para continuar\n");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        menu.menuAluno();
    }

    public void salvarDados(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            // Escreve o cabeçalho no arquivo
            writer.write("Nome,Matrícula,Curso,Especial,Matérias,Semestre trancado,Curso trancado,Pré-Requisitos");
            writer.newLine();

            // Escreve os dados de cada aluno
            for (Aluno aluno : alunos) {

                String preRequisitosStr = "";
                if (aluno.getPreRequisito() != null && !aluno.getPreRequisito().isEmpty()) {
                    preRequisitosStr = String.join(";", aluno.getPreRequisito());
                }

                writer.write(aluno.getNome() + ","
                        + aluno.getMatricula() + ","
                        + aluno.getCurso() + ","
                        + aluno.getEspecial() + ","
                        + aluno.getMateria() + ","
                        + aluno.getSemestreTrancado() + ","
                        + aluno.getCursoTrancado() + ","
                        + preRequisitosStr);
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
                String[] dados = linha.split(",",-1);
                if (dados.length == 8) {
                    String nome = dados[0].trim();
                    int matricula = Integer.parseInt(dados[1].trim());
                    String curso = dados[2].trim();
                    boolean especial = Boolean.parseBoolean(dados[3].trim());
                    int materias = Integer.parseInt(dados[4].trim());
                    boolean semestreTrancado = Boolean.parseBoolean(dados[5].trim());
                    boolean cursoTrancado = Boolean.parseBoolean(dados[6].trim());

                    Aluno aluno;
                    if (especial) {
                        aluno = new AlunoEspecial(nome, matricula, curso, especial, cursoTrancado, semestreTrancado);
                    } else {
                        aluno = new Aluno(nome, matricula, curso, especial, cursoTrancado, semestreTrancado);
                    }

                    if (dados.length > 7 && !dados[7].isEmpty()) {
                        String[] preReqs = dados[7].split(";");
                        for (String preRequisitos : preReqs) {
                            aluno.getPreRequisito().add(preRequisitos.trim());
                        }
                    }
                    aluno.setMateria(materias);

                    alunos.add(aluno);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os alunos: " + e.getMessage());
        }
    }

    public void trancamento() {
        Aluno alunoParaEditar = retornaAluno();
        System.out.println("\n" + alunoParaEditar.toString());

        System.out.println("\nVocê deseja:\n\n1 - Trancar/destrancar disciplina\n2 - Trancar/destrancar semestre\n3 - Trancar/destrancar curso\n4 - Voltar para o Menu Aluno\n");
        try (Scanner sc = new Scanner(System.in)) {

            int opçao = sc.nextInt();
            sc.nextLine();
            switch (opçao) {
                case 1 -> {
                    //implementar trancar disciplina
                }
                case 2 -> {
                    if (alunoParaEditar.getSemestreTrancado().equals(false)) {
                        System.out.println("\nDeseja trancar o semestre do/a " + alunoParaEditar.getNome() + "?\n\nDigite Sim para trancar ou Nao para voltar ao menu.");
                        String escolha = sc.nextLine();

                        if (escolha.equalsIgnoreCase("sim")) {
                            alunoParaEditar.setSemestreTrancado(true);
                            System.out.println("\nO semestre foi trancado\n");
                        }
                        menu.menuAluno();
                    }

                    System.out.println("\nDeseja destrancar o semestre do/a " + alunoParaEditar.getNome() + "?\n\nDigite Sim para destrancar ou Nao para voltar ao menu");
                    String escolha = sc.nextLine();
                    if (escolha.equalsIgnoreCase("sim")) {
                        alunoParaEditar.setSemestreTrancado(false);
                        System.out.println("\nO semestre foi destrancado\n");
                    }
                    menu.menuAluno();
                }
                case 3 -> {
                    if (alunoParaEditar.getCursoTrancado().equals(false)) {
                        System.out.println("\nDeseja trancar o curso do/a " + alunoParaEditar.getNome() + "?\n\nDigite Sim para trancar ou Nao para voltar ao menu.");
                        String escolha = sc.nextLine();

                        if (escolha.equalsIgnoreCase("sim")) {
                            alunoParaEditar.setCursoTrancado(true);
                            System.out.println("\nO curso foi trancado\n");
                        }
                        menu.menuAluno();
                    }

                    System.out.println("\nDeseja destrancar o curso do/a " + alunoParaEditar.getNome() + "?\n\nDigite Sim para destrancar ou Nao para voltar ao menu");

                    String escolha = sc.nextLine();
                    if (escolha.equalsIgnoreCase("sim")) {
                        alunoParaEditar.setCursoTrancado(false);
                        System.out.println("\nO curso foi trancado\n");
                    }
                    menu.menuAluno();
                }

                case 4 -> {
                    menu.menuAluno();
                }
            }

        }
    }

    private Aluno buscarAlunoPorMatricula(int matricula) {

        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public void removerAluno() {
        try (Scanner sc = new Scanner(System.in)) {
            Aluno alunoParaEditar = retornaAluno();

            if (alunoParaEditar == null) {
                System.out.println("\nMatrícula não encontrada\n");
                menu.menuAluno();
            }

            System.out.println("\n" + alunoParaEditar.toString());

            System.out.println("Deseja remover esse aluno?\n\nDigite Sim para remover e Nao para retornar ao menu.");
            String opcao = sc.nextLine();

            if (opcao.equalsIgnoreCase("sim")) {
                alunos.remove(alunoParaEditar);
                System.out.println("\nAluno removido com sucesso.\n");
                menu.menuAluno();
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            menu.menuAluno();
        }
    }

    public Aluno retornaAluno() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int matricula = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("\nDigite a matrícula do aluno que deseja realizar a operação\n");
            try {
                matricula = sc.nextInt();
                sc.nextLine();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("---------------------------------------");
                System.out.println("Entrada inválida! Por favor, digite um número.");
                System.out.println("---------------------------------------");
                sc.nextLine();
            }
        }

        Aluno alunoParaEditar = buscarAlunoPorMatricula(matricula);

        if (alunoParaEditar == null) {
            System.out.println("\nMatrícula não encontrada\n");
            menu.menuAluno();
        }
        return alunoParaEditar;
    }

    public void cadastrarAlunoNaTurma() {
        Aluno alunoParaCadastrar = retornaAluno();

        System.out.println("\n" + alunoParaCadastrar.toString() + "\n");

        if (alunoParaCadastrar.getCursoTrancado() == true || alunoParaCadastrar.getSemestreTrancado() == true) {
            System.out.println("\nEsse aluno não pode se matricular, pois seu semestre ou curso está trancado\n");
            menu.menuAluno();
        }

        if (alunoParaCadastrar instanceof AlunoEspecial esp) {
            if (esp.getMateria() >= 2) {
                System.out.println("\nO aluno " + esp.getNome() + " já esta matricululado em duas matérias\n");
                menu.menuAluno();
            }
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nDigite o código da disciplina que deseja cadastrar:\n");
            int codigo = sc.nextInt();
            sc.nextLine();
            Disciplina disciplinaParaCadastrar = disciplinaManager.buscarDisiciplinaPorCodigo(codigo);
            if (disciplinaParaCadastrar == null) {
                System.out.println("\nDisciplina não encontrada\n");
                menu.menuAluno();
            }
            boolean possuiPreRequisito = false;
            for (String preRequisto : alunoParaCadastrar.getPreRequisito()) {
                if (preRequisto.equalsIgnoreCase(disciplinaParaCadastrar.getPreRequisito())) {
                    possuiPreRequisito = true;
                    break;
                }
            }

            if (!possuiPreRequisito) {
                System.out.println("\nO aluno não possui o pré-requisito necessário para se matricular\n");
                menu.menuAluno();
            }

            List<Turma> turmas = disciplinaParaCadastrar.getTurmas();
            if (turmas.isEmpty()) {
                System.out.println("\nNenhuma turma cadastrada para essa disciplina\n");
                menu.menuAluno();
            }

            for (Turma turma : turmas) {
                for (Aluno aluno : turma.getListaAlunos()) {
                    if (aluno.getMatricula().equals(alunoParaCadastrar.getMatricula())) {
                        System.out.println("\nEsse aluno já está matriculado na disciplina.\n");
                        menu.menuAluno();
                    }
                }
            }

            System.out.println("\nTurmas cadastradas na disciplina " + disciplinaParaCadastrar.getNome() + "\n");

            for (Turma turma : turmas) {
                System.out.println("- Turma: " + turma.getNumeroDaTurma() + " | - Professor: " + turma.getProfessor()
                        + " | Semestre: " + turma.getSemestre()
                        + " | Sala: " + turma.getSala()
                        + " | Horário: " + turma.getHorario()
                        + "h | Tipo de Aula: " + turma.getTipoDeAula()
                        + " | Capacidade Máxima: " + turma.getCapacidadeMax()
                        + " | Número de vagas: " + turma.getVagas() + "\n");

            }

            System.out.println("\nDigite o número da turma que deseja cadastrar o aluno:\n");
            int escolha = sc.nextInt();
            sc.nextLine();

            for (Turma turma : turmas) {
                if (turma.getNumeroDaTurma().equals(escolha)) {
                    turma.getListaAlunos().add(alunoParaCadastrar);
                    turma.setVagas(turma.getVagas() - 1);
                    alunoParaCadastrar.setMateria(alunoParaCadastrar.getMateria() + 1);
                    System.out.println("Aluno " + alunoParaCadastrar.getNome() + " agora está em " + alunoParaCadastrar.getMateria() + " matérias.");
                    System.out.println("\nAluno " + alunoParaCadastrar.getNome() + " cadastradado em " + disciplinaParaCadastrar.getNome() + " na turma " + turma.getNumeroDaTurma() + "\n");
                    menu.menuAluno();
                }
            }

            System.out.println("\nEssa turma não existe\n");
            menu.menuAluno();

        }

    }

    public Aluno buscarAlunoPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return aluno;
            }
        }
        return null;
    }
}
