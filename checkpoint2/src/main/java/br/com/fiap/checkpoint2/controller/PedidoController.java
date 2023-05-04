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

import br.com.fiap.checkpoint2.dto.PedidoCreateDTO;
import br.com.fiap.checkpoint2.dto.PedidoResponseDTO;
import br.com.fiap.checkpoint2.dto.PedidoUpdateDTO;
import br.com.fiap.checkpoint2.model.PedidoModel;
import br.com.fiap.checkpoint2.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Manutenção de informações sobre os Clientes.")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseBody
    public PedidoResponseDTO save(@RequestBody PedidoCreateDTO novoPedido) {

        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setCodigoCliente(novoPedido.getCodigoCliente());
        pedidoModel.setDataPedido(novoPedido.getDataPedido());

        return pedidoService.save(pedidoModel);
    }

    @GetMapping("/{numeroPedido}")
    @ResponseBody
    public PedidoResponseDTO findById(@PathVariable Long numeroPedido) {
        return pedidoService.findById(numeroPedido);
    }

    @GetMapping
    @ResponseBody
    public List<PedidoResponseDTO> findAll() {
        return pedidoService.findAll();
    }

    @PutMapping("/{numeroPedido}")
    @ResponseBody
    public PedidoResponseDTO update(@PathVariable Long numeroPedido, @RequestBody PedidoUpdateDTO pedidoUpdate) {

        Boolean pedidoExiste = pedidoService.existsById(numeroPedido);

        if(!pedidoExiste) {
            throw new RuntimeException("Pedido " + numeroPedido + "não encontrado!");
        }
        
        PedidoModel pedido = new PedidoModel();

        pedido.setNumero_pedido(numeroPedido);
        pedido.setCodigoCliente(pedidoUpdate.getCodigoCliente());
        pedido.setDataPedido(pedidoUpdate.getDataPedido());

        return pedidoService.save(pedido);
    }

    @DeleteMapping("/{numeroPedido}")
    public void delete(@PathVariable Long numeroPedido) {

        Boolean pedidoExiste = pedidoService.existsById(numeroPedido);

        if(!pedidoExiste) {
            throw new RuntimeException("Pedido " + numeroPedido + " não encontrado!");
        }

        pedidoService.delete(numeroPedido);
    }
}
