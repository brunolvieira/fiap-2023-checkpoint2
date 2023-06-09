package br.com.fiap.checkpoint2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoModel {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoProduto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "data_garantia")
    private LocalDate dataGarantia;

    @Column(name = "em_estoque")
    private Integer emEstoque;

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

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
