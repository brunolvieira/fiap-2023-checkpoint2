package br.com.fiap.checkpoint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.checkpoint2.model.ItensPedidoModel;

public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Long> {
    
}
