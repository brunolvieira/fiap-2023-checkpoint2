package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ItensPedidoResponseDTO;
import br.com.fiap.checkpoint2.model.ItensPedidoModel;
import br.com.fiap.checkpoint2.repository.ItensPedidoRepository;

@Service
public class ItensPedidoService {
    
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    public ItensPedidoResponseDTO save(ItensPedidoModel novoItensPedido) {
        return modelToDto(itensPedidoRepository.save(novoItensPedido));
    }

    public ItensPedidoResponseDTO findById(Long sequencia) {
        return modelToDto(itensPedidoRepository.findById(sequencia).get());
    }

    public List<ItensPedidoResponseDTO> findAll() {

        List<ItensPedidoModel> listModel = itensPedidoRepository.findAll();
        List<ItensPedidoResponseDTO> listDto = new ArrayList<>();

        for(ItensPedidoModel itensPedido : listModel) {
            listDto.add(modelToDto(itensPedido));
        }

        return listDto;
    }

    public ItensPedidoResponseDTO update(Long sequencia, ItensPedidoModel itensPedido) {

        Optional<ItensPedidoModel> opItensPedidoUpdate = itensPedidoRepository.findById(sequencia);

        if(!opItensPedidoUpdate.isPresent()) {
            throw new RuntimeException("Código: " + sequencia + " nao encontrado!");
        }

        ItensPedidoModel itensPedidoUpdate = opItensPedidoUpdate.get();

        itensPedidoUpdate.setNumeroPedido(itensPedido.getNumeroPedido());
        itensPedidoUpdate.setCodigoProduto(itensPedido.getCodigoProduto());
        itensPedidoUpdate.setQuantidade(itensPedido.getQuantidade());
        itensPedidoUpdate.setValorTotal(itensPedido.getValorTotal()
        );

        return modelToDto(itensPedidoRepository.save(itensPedidoUpdate));
    }

    public void delete(Long sequencia) {
        Optional<ItensPedidoModel> opItensPedidoDelete = itensPedidoRepository.findById(sequencia);

        if(!opItensPedidoDelete.isPresent()) {
            throw new RuntimeException("Código: " + sequencia + " não encontrado!");
        }

        itensPedidoRepository.delete(opItensPedidoDelete.get());
    }

    public ItensPedidoResponseDTO modelToDto(ItensPedidoModel model) {

        ItensPedidoResponseDTO dto = new ItensPedidoResponseDTO();

        dto.setSequencia(model.getSequencia());
        dto.setNumeroPedido(model.getNumeroPedido());
        dto.setCodigoProduto(model.getCodigoProduto());
        dto.setQuantidade(model.getQuantidade());
        dto.setValorTotal(model.getValorTotal());

        return dto;
    }
}
