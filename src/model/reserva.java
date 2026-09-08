package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private String token;
    private Cliente cliente; // Chave estrangeira de Cliente
    private String categoriaVeiculo;
    private LocalDateTime drp; // Data de Retirada Prevista
    private LocalDateTime ddp; // Data de Devolução Prevista
    private String localRetirada;
    private String opcionais;
    private BigDecimal estimativaPreco;
    private String status;
    private LocalDateTime dataCriacao;

    // Construtor padrão
    public Reserva() {
        this.status = "ATIVA";
        this.dataCriacao = LocalDateTime.now();
    }

    // Construtor completo
    public Reserva(int id, String token, Cliente cliente, String categoriaVeiculo, 
                   LocalDateTime drp, LocalDateTime ddp, String localRetirada, 
                   String opcionais, BigDecimal estimativaPreco, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.token = token;
        this.cliente = cliente;
        this.categoriaVeiculo = categoriaVeiculo;
        this.drp = drp;
        this.ddp = ddp;
        this.localRetirada = localRetirada;
        this.opcionais = opcionais;
        this.estimativaPreco = estimativaPreco;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    // Construtor para nova reserva
    public Reserva(String token, Cliente cliente, String categoriaVeiculo, 
                   LocalDateTime drp, LocalDateTime ddp, String localRetirada, 
                   String opcionais, BigDecimal estimativaPreco) {
        this.token = token;
        this.cliente = cliente;
        this.categoriaVeiculo = categoriaVeiculo;
        this.drp = drp;
        this.ddp = ddp;
        this.localRetirada = localRetirada;
        this.opcionais = opcionais;
        this.estimativaPreco = estimativaPreco;
        this.status = "ATIVA";
        this.dataCriacao = LocalDateTime.now();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public LocalDateTime getDrp() {
        return drp;
    }

    public void setDrp(LocalDateTime drp) {
        this.drp = drp;
    }

    public LocalDateTime getDdp() {
        return ddp;
    }

    public void setDdp(LocalDateTime ddp) {
        this.ddp = ddp;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public String getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(String opcionais) {
        this.opcionais = opcionais;
    }

    public BigDecimal getEstimativaPreco() {
        return estimativaPreco;
    }

    public void setEstimativaPreco(BigDecimal estimativaPreco) {
        this.estimativaPreco = estimativaPreco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}