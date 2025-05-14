
public class Disciplina {

    private String nome;
    private Integer codigo;
    private Integer cargaHoraria;
    private String preRequisito;

    public Disciplina() {
        this.nome = "";
        this.codigo = 0;
        this.cargaHoraria = 0;
        this.preRequisito = "";
    }

    public Disciplina(String nome, Integer codigo, Integer cargaHoraria, String preRequisito) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisito = preRequisito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo; 
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }
}
