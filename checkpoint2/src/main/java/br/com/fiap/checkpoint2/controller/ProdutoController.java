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

import br.com.fiap.checkpoint2.dto.ProdutoCreateDTO;
import br.com.fiap.checkpoint2.dto.ProdutoResponseDTO;
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
    public ProdutoResponseDTO save(@RequestBody ProdutoCreateDTO novoProduto) {

        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setNome(novoProduto.getNome());
        produtoModel.setPreco(novoProduto.getPreco());
        produtoModel.setDataValidade(novoProduto.getDataValidade());
        produtoModel.setDataGarantia(novoProduto.getDataGarantia());
        produtoModel.setEmEstoque(novoProduto.getEmEstoque());

        return produtoService.save(produtoModel);
    }

    @GetMapping("/{codigoProduto}")
    @ResponseBody
    public ProdutoResponseDTO findById(@PathVariable Long codigoProduto) {
        return produtoService.findById(codigoProduto);
    }

    @GetMapping
    @ResponseBody
    public List<ProdutoResponseDTO> findAll() {
        return produtoService.findAll();
    }

    @PutMapping("/{codigoProduto}")
    @ResponseBody
    public ProdutoResponseDTO update(@PathVariable Long codigoProduto, @RequestBody ProdutoUpdateDto produtoUpdate) {

        Boolean produtoExiste = produtoService.existsById(codigoProduto);

        if(!produtoExiste) {
            throw new RuntimeException("Produto " + codigoProduto + " não encontrado!");
        }

        ProdutoModel produto = new ProdutoModel();

        produto.setCodigoProduto(codigoProduto);
        produto.setNome(produtoUpdate.getNome());
        produto.setPreco(produtoUpdate.getPreco());
        produto.setDataValidade(produtoUpdate.getDataValidade());
        produto.setDataGarantia(produtoUpdate.getDataGarantia());
        produto.setEmEstoque(produtoUpdate.getEmEstoque());

        return produtoService.save(produto);
    }

    @DeleteMapping("/{codigoProduto}")
    public void delete(@PathVariable Long codigoProduto) {

        Boolean produtoExiste = produtoService.existsById(codigoProduto);

        if(!produtoExiste) {
            throw new RuntimeException("Produto " + codigoProduto + " não encontrado!");
        }
        
        produtoService.delete(codigoProduto);
    }
}
