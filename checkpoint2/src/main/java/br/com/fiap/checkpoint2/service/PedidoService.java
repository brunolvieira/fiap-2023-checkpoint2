package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.PedidoResponseDTO;
import br.com.fiap.checkpoint2.model.PedidoModel;
import br.com.fiap.checkpoint2.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository pedidoRepository;

    public Boolean existsById(Long numeroPedido) {
        return pedidoRepository.existsById(numeroPedido);
    }

    public PedidoResponseDTO save(PedidoModel novoPedido) {
        return modelToDto(pedidoRepository.save(novoPedido));
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

    public void delete(Long numeroPedido) {
        pedidoRepository.delete(pedidoRepository.findById(numeroPedido).get());
    }

    private static PedidoResponseDTO modelToDto(PedidoModel model) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setNumeroPedido(model.getNumero_pedido());
        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setDataPedido(model.getDataPedido());

        return dto;
    }
}
