package br.com.fiap.checkpoint2.dto;

import java.time.LocalDate;

public class ProdutoUpdateDto {

    private String nome;
    private Double preco;
    private LocalDate dataValidade;
    private LocalDate dataGarantia;
    private Integer emEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataGarantia() {
        return dataGarantia;
    }

    public void setDataGarantia(LocalDate dataGarantia) {
        this.dataGarantia = dataGarantia;
    }

    public Integer getEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(Integer emEstoque) {
        this.emEstoque = emEstoque;
    }
}
