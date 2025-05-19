
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DisciplinaManager {

    private List<Disciplina> listaDisciplinas = new ArrayList<>();
    private final String nomeArquivo = "data/disciplina.csv";
    private final File arquivo = new File(nomeArquivo);
    private Menu menu;

    public void setMenu(Menu menu) {
        this.menu = menu;
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
}
