package br.com.fiap.checkpoint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint2.dto.ProdutoCreateDto;
import br.com.fiap.checkpoint2.dto.ProdutoDto;
import br.com.fiap.checkpoint2.dto.ProdutoUpdateDto;
import br.com.fiap.checkpoint2.model.ProdutoModel;
import br.com.fiap.checkpoint2.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Manutenção de informações sobre os Produtos.")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseBody
    public ProdutoDto save(@RequestBody ProdutoCreateDto produtoDto) {

        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setNome(produtoDto.getNome());
        produtoModel.setPreco(produtoDto.getPreco());
        produtoModel.setDataValidade(produtoDto.getDataValidade());
        produtoModel.setDataGarantia(produtoDto.getDataGarantia());
        produtoModel.setEmEstoque(produtoDto.getEmEstoque());

        return produtoService.save(produtoModel);
    }

    @GetMapping("/{codigoProduto}")
    @ResponseBody
    public ProdutoDto findById(@PathVariable Long codigoProduto) {
        return produtoService.findById(codigoProduto);
    }

    @GetMapping
    @ResponseBody
    public List<ProdutoDto> findAll() {
        return produtoService.findAll();
    }

    @PutMapping("/{codigoProduto}")
    @ResponseBody
    public ProdutoDto update(@PathVariable Long codigoProduto, @RequestBody ProdutoUpdateDto produtoDto) {

        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setNome(produtoDto.getNome());
        produtoModel.setPreco(produtoDto.getPreco());
        produtoModel.setDataValidade(produtoDto.getDataValidade());
        produtoModel.setDataGarantia(produtoDto.getDataGarantia());
        produtoModel.setEmEstoque(produtoDto.getEmEstoque());

        return produtoService.update(codigoProduto, produtoModel);
    }

    @DeleteMapping("/{codigoProduto}")
    public void delete(@PathVariable Long codigoProduto) {
        produtoService.delete(codigoProduto);
    }
}
