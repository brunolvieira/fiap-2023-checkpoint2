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

import br.com.fiap.checkpoint2.dto.ItemPedidoCreateDTO;
import br.com.fiap.checkpoint2.dto.ItemPedidoResponseDTO;
import br.com.fiap.checkpoint2.dto.ItemPedidoUpdateDTO;
import br.com.fiap.checkpoint2.model.ItemPedidoModel;
import br.com.fiap.checkpoint2.service.ItemPedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/itensPedidos")
@Tag(name = "ItensPedidos", description = "Manutenção de informações sobre os Itens de Pedidos")
public class ItemPedidoController {
    
    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    @ResponseBody
    public ItemPedidoResponseDTO save(@RequestBody ItemPedidoCreateDTO novoItemPedido) {

        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();

        itemPedidoModel.setNumeroPedido(novoItemPedido.getNumeroPedido());
        itemPedidoModel.setCodigoProduto(novoItemPedido.getCodigoProduto());
        itemPedidoModel.setQuantidade(novoItemPedido.getQuantidade());
        itemPedidoModel.setValorTotal(novoItemPedido.getValorTotal());

        return itemPedidoService.save(itemPedidoModel);
    }

    @GetMapping("/{sequencia}")
    @ResponseBody
    public ItemPedidoResponseDTO findById(@PathVariable Long sequencia) {
        return itemPedidoService.findById(sequencia);
    }

    @GetMapping
    @ResponseBody
    public List<ItemPedidoResponseDTO> findAll() {
        return itemPedidoService.findAll();
    }

    @PutMapping("/{sequencia}")
    @ResponseBody
    public ItemPedidoResponseDTO update(@PathVariable Long sequencia, @RequestBody ItemPedidoUpdateDTO itemPedidoUpdate) {

       Boolean itemPedidoExiste = itemPedidoService.existsById(sequencia);

        if(!itemPedidoExiste) {
            throw new RuntimeException("Código: " + sequencia + " nao encontrado!");
        }

        ItemPedidoModel itemPedido = new ItemPedidoModel();

        itemPedido.setSequencia(sequencia);
        itemPedido.setNumeroPedido(itemPedidoUpdate.getNumeroPedido());
        itemPedido.setCodigoProduto(itemPedidoUpdate.getCodigoProduto());
        itemPedido.setQuantidade(itemPedidoUpdate.getQuantidade());
        itemPedido.setValorTotal(itemPedidoUpdate.getValorTotal());

        return itemPedidoService.save(itemPedido);
    }

    @DeleteMapping("/{sequencia}")
    public void delete(@PathVariable Long sequencia) {

        Boolean itemPedidoExiste = itemPedidoService.existsById(sequencia);

        if(!itemPedidoExiste) {
            throw new RuntimeException("Código: " + sequencia + " não encontrado!");
        }

        itemPedidoService.delete(sequencia);
    }
}
