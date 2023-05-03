package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.PedidoDto;
import br.com.fiap.checkpoint2.model.PedidoModel;
import br.com.fiap.checkpoint2.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository pedidoRepository;

    public PedidoDto save(PedidoModel pedidoModel) {
        return modelToDto(pedidoRepository.save(pedidoModel));
    }

    public PedidoDto findById(Long numeroPedido) {
        return modelToDto(pedidoRepository.findById(numeroPedido).get());
    }

    public List<PedidoDto> findAll() {

        List<PedidoModel> listModel = pedidoRepository.findAll();
        List<PedidoDto> listDto = new ArrayList<>();

        for(PedidoModel pedido : listModel) {
            listDto.add(modelToDto(pedido));
        }

        return listDto;
    }

    public PedidoDto update(Long numeroPedido, PedidoModel pedidoModel) {

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

    public PedidoDto modelToDto(PedidoModel model) {

        PedidoDto dto = new PedidoDto();

        dto.setNumeroPedido(model.getNumero_pedido());
        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setDataPedido(model.getDataPedido());

        return dto;
    }
}
