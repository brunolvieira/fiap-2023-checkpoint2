package br.com.fiap.checkpoint2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ProdutoDto;
import br.com.fiap.checkpoint2.model.ProdutoModel;
import br.com.fiap.checkpoint2.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDto save(ProdutoModel produtoModel) {
        return modelToDto(produtoRepository.save(produtoModel));
    }

    public ProdutoDto findById(Long codigoProduto) {
        ProdutoModel produto = produtoRepository.findById(codigoProduto).get();
        return modelToDto(produto);
    }

    public List<ProdutoDto> findAll() {

        List<ProdutoModel>listModel = produtoRepository.findAll();
        List<ProdutoDto> listDto = new ArrayList<>();

        for(ProdutoModel produto : listModel) {
            listDto.add(modelToDto(produto));
        }

        return listDto;
    }

    public ProdutoDto update(Long codigoProduto, ProdutoModel produtoModel) {

        Optional<ProdutoModel> opProdutoUpdate = produtoRepository.findById(codigoProduto);

        if(!opProdutoUpdate.isPresent()) {
            throw new RuntimeException("Código: " + codigoProduto + " nao encontrado!");
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
            throw new RuntimeException("Código: " + codigoProduto + " nao encontrado!");
        }

        ProdutoModel produtoDelete = opProdutoDelete.get();

        produtoRepository.delete(produtoDelete);
    }

    public ProdutoDto modelToDto(ProdutoModel model) {

        ProdutoDto dto = new ProdutoDto();

        dto.setCodigoProduto(model.getCodigoProduto());
        dto.setNome(model.getNome());
        dto.setPreco(model.getPreco());
        dto.setDataValidade(model.getDataValidade());
        dto.setDataGarantia(model.getDataGarantia());
        dto.setEmEstoque(model.getEmEstoque());

        return dto;
    }
}
