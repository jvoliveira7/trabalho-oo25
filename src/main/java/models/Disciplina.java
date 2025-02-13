package models;

public class Disciplina {
    private String codigo;
    private String nome;
    private String ementa;
    private int cargaHoraria;
    private String siapeProfessor; // SIAPE do professor responsável

    // Construtor vazio (obrigatório para o Javalin)
    public Disciplina() {}

    // Construtor com todos os campos
    public Disciplina(String codigo, String nome, String ementa, int cargaHoraria, String siapeProfessor) {
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
        this.siapeProfessor = siapeProfessor;
    }

    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmenta() { return ementa; }
    public void setEmenta(String ementa) { this.ementa = ementa; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getSiapeProfessor() { return siapeProfessor; }
    public void setSiapeProfessor(String siapeProfessor) { this.siapeProfessor = siapeProfessor; }
}