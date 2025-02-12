package models;

public class Disciplina {
    private String codigo;
    private String nome;
    private String ementa;
    private int cargaHoraria;
    private Professor professorResponsavel;

    public Disciplina(String codigo, String nome, String ementa, int cargaHoraria, Professor professorResponsavel) {
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
        this.professorResponsavel = professorResponsavel;
    }


    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmenta() { return ementa; }
    public void setEmenta(String ementa) { this.ementa = ementa; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public Professor getProfessorResponsavel() { return professorResponsavel; }
    public void setProfessorResponsavel(Professor professorResponsavel) { this.professorResponsavel = professorResponsavel; }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", ementa='" + ementa + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", professorResponsavel=" + professorResponsavel +
                '}';
    }
}