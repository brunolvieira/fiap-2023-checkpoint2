package br.com.fiap.checkpoint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.checkpoint2.model.ItemPedidoModel;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Long> {
    
}
