package br.com.fiap.checkpoint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint2.dto.ItensPedidoCreateDTO;
import br.com.fiap.checkpoint2.dto.ItensPedidoResponseDTO;
import br.com.fiap.checkpoint2.dto.ItensPedidoUpdateDTO;
import br.com.fiap.checkpoint2.model.ItensPedidoModel;
import br.com.fiap.checkpoint2.service.ItensPedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/itensPedidos")
@Tag(name = "ItensPedidos", description = "Manutenção de informações sobre os Itens de Pedidos")
public class ItensPedidoController {
    
    @Autowired
    private ItensPedidoService itensPedidoService;

    @PostMapping
    @ResponseBody
    public ItensPedidoResponseDTO save(@RequestBody ItensPedidoCreateDTO novoItensPedido) {

        ItensPedidoModel itensPedidoModel = new ItensPedidoModel();

        itensPedidoModel.setNumeroPedido(novoItensPedido.getNumeroPedido());
        itensPedidoModel.setCodigoProduto(novoItensPedido.getCodigoProduto());
        itensPedidoModel.setQuantidade(novoItensPedido.getQuantidade());
        itensPedidoModel.setValorTotal(novoItensPedido.getValorTotal());

        return itensPedidoService.save(itensPedidoModel);
    }

    @GetMapping("/{sequencia}")
    @ResponseBody
    public ItensPedidoResponseDTO findById(@PathVariable Long sequencia) {
        return itensPedidoService.findById(sequencia);
    }

    @GetMapping
    @ResponseBody
    public List<ItensPedidoResponseDTO> findAll() {
        return itensPedidoService.findAll();
    }

    @PutMapping("/{sequencia}")
    @ResponseBody
    public ItensPedidoResponseDTO update(@PathVariable Long sequencia, @RequestBody ItensPedidoUpdateDTO updateDTO) {

        ItensPedidoModel itensPedidoModel = new ItensPedidoModel();

        itensPedidoModel.setNumeroPedido(updateDTO.getNumeroPedido());
        itensPedidoModel.setCodigoProduto(updateDTO.getCodigoProduto());
        itensPedidoModel.setQuantidade(updateDTO.getQuantidade());
        itensPedidoModel.setValorTotal(updateDTO.getValorTotal());

        return itensPedidoService.update(sequencia, itensPedidoModel);
    }

    @DeleteMapping("/{sequencia}")
    public void delete(@PathVariable Long sequencia) {
        itensPedidoService.delete(sequencia);
    }
}
