package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Locacao {

    private int id;
    private Reserva reserva;
    private Cliente cliente;
    private Funcionario funcionario;
    private Carro carro;
    private LocalDateTime dataRetirada;
    private LocalDateTime ddp; // Data de devolução prevista
    private LocalDateTime ddr; // Data de devolução real
    private BigDecimal valorCaucao;
    private BigDecimal valorTotal;
    private String vistoriaDevolucao;
    private String status;
    private LocalDateTime dataCriacao;

    // Construtor padrão
    public Locacao() {
        this.status = "ATIVA";
        this.dataCriacao = LocalDateTime.now();
    }

    // Construtor completo
    public Locacao(int id, Reserva reserva, Cliente cliente, Funcionario funcionario, Carro carro, 
                   LocalDateTime dataRetirada, LocalDateTime ddp, LocalDateTime ddr, 
                   BigDecimal valorCaucao, BigDecimal valorTotal, String vistoriaDevolucao, 
                   String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.reserva = reserva;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.carro = carro;
        this.dataRetirada = dataRetirada;
        this.ddp = ddp;
        this.ddr = ddr;
        this.valorCaucao = valorCaucao;
        this.valorTotal = valorTotal;
        this.vistoriaDevolucao = vistoriaDevolucao;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    // Construtor para abertura de locação
    public Locacao(Reserva reserva, Cliente cliente, Funcionario funcionario, Carro carro,
                   LocalDateTime dataRetirada, LocalDateTime ddp, BigDecimal valorCaucao) {
        this.reserva = reserva;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.carro = carro;
        this.dataRetirada = dataRetirada;
        this.ddp = ddp;
        this.valorCaucao = valorCaucao;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDateTime getDdp() {
        return ddp;
    }

    public void setDdp(LocalDateTime ddp) {
        this.ddp = ddp;
    }

    public LocalDateTime getDdr() {
        return ddr;
    }

    public void setDdr(LocalDateTime ddr) {
        this.ddr = ddr;
    }

    public BigDecimal getValorCaucao() {
        return valorCaucao;
    }

    public void setValorCaucao(BigDecimal valorCaucao) {
        this.valorCaucao = valorCaucao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getVistoriaDevolucao() {
        return vistoriaDevolucao;
    }

    public void setVistoriaDevolucao(String vistoriaDevolucao) {
        this.vistoriaDevolucao = vistoriaDevolucao;
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