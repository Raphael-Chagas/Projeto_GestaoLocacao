package model;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private String cargo;

    // Construtor padrão
    public Funcionario() {
    }

    // Construtor completo
    public Funcionario(int id, String nome, String cpf, String login, String senha, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    // Construtor para cadastro (sem id)
    public Funcionario(String nome, String cpf, String login, String senha, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}