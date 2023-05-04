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
    public PedidoResponseDTO save(@RequestBody PedidoCreateDTO pedidoDto) {

        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setCodigoCliente(pedidoDto.getCodigoCliente());
        pedidoModel.setDataPedido(pedidoDto.getDataPedido());

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
    public PedidoResponseDTO update(@PathVariable Long numeroPedido, @RequestBody PedidoUpdateDTO updateDto) {
        
        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setCodigoCliente(updateDto.getCodigoCliente());
        pedidoModel.setDataPedido(updateDto.getDataPedido());

        return pedidoService.update(numeroPedido, pedidoModel);
    }

    @DeleteMapping("/{numeroPedido}")
    public void delete(@PathVariable Long numeroPedido) {
        pedidoService.delete(numeroPedido);
    }
}
