package models;

public class Escola {
    private String nome;
    private String endereco;
    private String contato;

    // Construtor vazio (obrigatório para o Javalin)
    public Escola() {}

    // Construtor com todos os campos
    public Escola(String nome, String endereco, String contato) {
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}