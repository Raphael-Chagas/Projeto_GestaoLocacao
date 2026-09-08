package model;

import java.time.LocalDateTime;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String cnh;
    private String email;
    private String telefone;
    private String nec; // Necessidades especiais do cliente
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public Cliente() {
        this.ativo = true;
        this.dataCadastro = LocalDateTime.now();
    }

    public Cliente(int id, String nome, String cpf, String cnh, String email, String telefone, String nec, Boolean ativo, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.email = email;
        this.telefone = telefone;
        this.nec = nec;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public Cliente(String nome, String cpf, String cnh, String email, String telefone, String nec) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.email = email;
        this.telefone = telefone;
        this.nec = nec;
        this.ativo = true;
    }

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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNec() {
        return nec;
    }

    public void setNec(String nec) {
        this.nec = nec;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}