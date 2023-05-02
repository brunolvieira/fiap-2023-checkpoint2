package br.com.fiap.checkpoint2.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoModel {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero_pedido;

    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @Column(name = "data_pedido")
    private Date dataPedido;

    public Long getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(Long numeroPedido) {
        this.numero_pedido = numeroPedido;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
}
