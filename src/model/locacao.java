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
    private LocalDateTime ddp; //Data de devolução prevista
    private LocalDateTime ddr; //Data de devolução Real
    private BigDecimal valorCaucao;
    private BigDecimal valorTotal;
    private String vistoriaDevolucao;
    private String status;
    private LocalDateTime dataCriacao;

    public Locacao() {
        this.status = "ATIVA";
        this.dataCriacao = LocalDateTime.now();
    }

    // Construtor completo
    public Locacao(int id, Reserva reserva, Cliente cliente, Funcionario funcionario, Veiculo veiculo, LocalDateTime dataRetirada, LocalDateTime ddp, LocalDateTime ddr, BigDecimal valorCaucao, BigDecimal valorTotal, String vistoriaDevolucao, String status, LocalDateTime dataCriacao) {
        this.id = id;
        this.reserva = reserva;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = ddp;
        this.dataDevolucaoReal = ddp;
        this.valorCaucao = valorCaucao;
        this.valorTotal = valorTotal;
        this.vistoriaDevolucao = vistoriaDevolucao;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Locacao(Reserva reserva, Cliente cliente, Funcionario funcionario, Veiculo veiculo,
                   LocalDateTime dataRetirada, LocalDateTime ddp, BigDecimal valorCaucao) {
        this.reserva = reserva;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = ddp;
        this.valorCaucao = valorCaucao;
        this.status = "ATIVA";
        this.dataCriacao = LocalDateTime.now();
    }

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setddp(LocalDateTime ddp) {
        this.ddp = ddp;
    }

    public LocalDateTime getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setddr(LocalDateTime ddr) {
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