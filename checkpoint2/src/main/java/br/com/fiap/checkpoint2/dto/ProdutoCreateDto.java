package br.com.fiap.checkpoint2.dto;

import java.util.Date;

public class ProdutoCreateDto {

    private String nome;
    private Double preco;
    private Date dataValidade;
    private Date dataGarantia;
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

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Date getDataGarantia() {
        return dataGarantia;
    }

    public void setDataGarantia(Date dataGarantia) {
        this.dataGarantia = dataGarantia;
    }

    public Integer getEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(Integer emEstoque) {
        this.emEstoque = emEstoque;
    }
}