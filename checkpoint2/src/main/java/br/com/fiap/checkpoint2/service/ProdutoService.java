package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ProdutoResponseDTO;
import br.com.fiap.checkpoint2.model.ProdutoModel;
import br.com.fiap.checkpoint2.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Boolean existsById(Long codigoProduto) {
        return produtoRepository.existsById(codigoProduto);
    }

    public ProdutoResponseDTO save(ProdutoModel novoProduto) {
        return modelToDto(produtoRepository.save(novoProduto));
    }

    public ProdutoResponseDTO findById(Long codigoProduto) {
        return modelToDto(produtoRepository.findById(codigoProduto).get());
    }

    public List<ProdutoResponseDTO> findAll() {

        List<ProdutoModel> listModel = produtoRepository.findAll();
        List<ProdutoResponseDTO> listDto = new ArrayList<>();

        for(ProdutoModel produto : listModel) {
            listDto.add(modelToDto(produto));
        }

        return listDto;
    }

    public void delete(Long codigoProduto) {
        produtoRepository.delete(produtoRepository.findById(codigoProduto).get());
    }

    private static ProdutoResponseDTO modelToDto(ProdutoModel model) {

        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setCodigoProduto(model.getCodigoProduto());
        dto.setNome(model.getNome());
        dto.setPreco(model.getPreco());
        dto.setDataValidade(model.getDataValidade());
        dto.setDataGarantia(model.getDataGarantia());
        dto.setEmEstoque(model.getEmEstoque());

        return dto;
    }
}
