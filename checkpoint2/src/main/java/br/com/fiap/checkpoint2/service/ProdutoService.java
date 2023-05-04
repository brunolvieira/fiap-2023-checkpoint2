package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ProdutoResponseDTO;
import br.com.fiap.checkpoint2.model.ProdutoModel;
import br.com.fiap.checkpoint2.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO save(ProdutoModel produtoModel) {
        return modelToDto(produtoRepository.save(produtoModel));
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

    public ProdutoResponseDTO update(Long codigoProduto, ProdutoModel produtoModel) {

        Optional<ProdutoModel> opProdutoUpdate = produtoRepository.findById(codigoProduto);

        if(!opProdutoUpdate.isPresent()) {
            throw new RuntimeException("Código: " + codigoProduto + " não encontrado!");
        }

        ProdutoModel produtoUpdate = opProdutoUpdate.get();

        produtoUpdate.setNome(produtoModel.getNome());
        produtoUpdate.setPreco(produtoModel.getPreco());
        produtoUpdate.setDataValidade(produtoModel.getDataValidade());
        produtoUpdate.setDataGarantia(produtoModel.getDataGarantia());
        produtoUpdate.setEmEstoque(produtoModel.getEmEstoque());

        return modelToDto(produtoRepository.save(produtoUpdate));
    }

    public void delete(Long codigoProduto) {

        Optional<ProdutoModel> opProdutoDelete = produtoRepository.findById(codigoProduto);

        if(!opProdutoDelete.isPresent()) {
            throw new RuntimeException("Código: " + codigoProduto + " não encontrado!");
        }

        produtoRepository.delete(opProdutoDelete.get());
    }

    public ProdutoResponseDTO modelToDto(ProdutoModel model) {

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
