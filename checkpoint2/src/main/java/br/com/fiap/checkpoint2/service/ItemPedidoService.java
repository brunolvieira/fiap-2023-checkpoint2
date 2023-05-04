package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ItemPedidoResponseDTO;
import br.com.fiap.checkpoint2.model.ItemPedidoModel;
import br.com.fiap.checkpoint2.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Boolean existsById(Long sequencia) {
        return itemPedidoRepository.existsById(sequencia);
    }

    public ItemPedidoResponseDTO save(ItemPedidoModel novoItemPedido) {
        return modelToDto(itemPedidoRepository.save(novoItemPedido));
    }

    public ItemPedidoResponseDTO findById(Long sequencia) {
        return modelToDto(itemPedidoRepository.findById(sequencia).get());
    }

    public List<ItemPedidoResponseDTO> findAll() {

        List<ItemPedidoModel> listModel = itemPedidoRepository.findAll();
        List<ItemPedidoResponseDTO> listDto = new ArrayList<>();

        for(ItemPedidoModel itemPedido : listModel) {
            listDto.add(modelToDto(itemPedido));
        }

        return listDto;
    }

    public void delete(Long sequencia) {
        itemPedidoRepository.delete(itemPedidoRepository.findById(sequencia).get());
    }

    private static ItemPedidoResponseDTO modelToDto(ItemPedidoModel model) {

        ItemPedidoResponseDTO dto = new ItemPedidoResponseDTO();

        dto.setSequencia(model.getSequencia());
        dto.setNumeroPedido(model.getNumeroPedido());
        dto.setCodigoProduto(model.getCodigoProduto());
        dto.setQuantidade(model.getQuantidade());
        dto.setValorTotal(model.getValorTotal());

        return dto;
    }
}
