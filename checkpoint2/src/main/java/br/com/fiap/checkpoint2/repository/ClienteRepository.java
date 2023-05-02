package br.com.fiap.checkpoint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.checkpoint2.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    
}
