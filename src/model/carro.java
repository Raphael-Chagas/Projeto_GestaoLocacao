package model;

import java.time.LocalDateTime;

public class Carro {

    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String categoria;
    private String quilometragem;
    private String status;
    private LocalDateTime dataCadastro;

    public Carro() {
        this.quilometragem = 0;
        this.status = "DISPONIVEL";
        this.dataCadastro = LocalDateTime.now();
    }

    public Carro(int id, String placa, String marca, String modelo, String categoria, String quilometragem, String status, LocalDateTime dataCadastro) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.quilometragem = quilometragem;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public Carro(String placa, String marca, String modelo, String categoria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.quilometragem = "0";
        this.status = "DISPONIVEL";
        this.dataCadastro = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}