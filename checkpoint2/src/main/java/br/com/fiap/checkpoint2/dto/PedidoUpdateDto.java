package br.com.fiap.checkpoint2.dto;

import java.time.LocalDate;

public class PedidoUpdateDTO {
    
    private Long codigoCliente;
    private LocalDate dataPedido;

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
}
