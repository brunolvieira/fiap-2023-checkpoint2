package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.PedidoResponseDTO;
import br.com.fiap.checkpoint2.model.PedidoModel;
import br.com.fiap.checkpoint2.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository pedidoRepository;

    public PedidoResponseDTO save(PedidoModel pedidoModel) {
        return modelToDto(pedidoRepository.save(pedidoModel));
    }

    public PedidoResponseDTO findById(Long numeroPedido) {
        return modelToDto(pedidoRepository.findById(numeroPedido).get());
    }

    public List<PedidoResponseDTO> findAll() {

        List<PedidoModel> listModel = pedidoRepository.findAll();
        List<PedidoResponseDTO> listDto = new ArrayList<>();

        for(PedidoModel pedido : listModel) {
            listDto.add(modelToDto(pedido));
        }

        return listDto;
    }

    public PedidoResponseDTO update(Long numeroPedido, PedidoModel pedidoModel) {

        Optional<PedidoModel> opPedidoUpdate = pedidoRepository.findById(numeroPedido);

        if(!opPedidoUpdate.isPresent()) {
            throw new RuntimeException("Código: " + numeroPedido + "não encontrado!");
        }

        PedidoModel pedidoUpdate = opPedidoUpdate.get();

        pedidoUpdate.setCodigoCliente(pedidoModel.getCodigoCliente());
        pedidoUpdate.setDataPedido(pedidoModel.getDataPedido());

        return modelToDto(pedidoRepository.save(pedidoUpdate));
    }

    public void delete(Long numeroPedido) {

         Optional<PedidoModel> opPedidoDelete = pedidoRepository.findById(numeroPedido);

         if(!opPedidoDelete.isPresent()) {
            throw new RuntimeException("Código: " + numeroPedido + " não encontrado!");
        }

        pedidoRepository.delete(opPedidoDelete.get());
    }

    public PedidoResponseDTO modelToDto(PedidoModel model) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setNumeroPedido(model.getNumero_pedido());
        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setDataPedido(model.getDataPedido());

        return dto;
    }
}
