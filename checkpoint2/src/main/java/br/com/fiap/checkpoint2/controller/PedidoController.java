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

import br.com.fiap.checkpoint2.dto.PedidoCreateDto;
import br.com.fiap.checkpoint2.dto.PedidoDto;
import br.com.fiap.checkpoint2.dto.PedidoUpdateDto;
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
    public PedidoDto save(@RequestBody PedidoCreateDto pedidoDto) {

        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setCodigoCliente(pedidoDto.getCodigoCliente());
        pedidoModel.setDataPedido(pedidoDto.getDataPedido());

        return pedidoService.save(pedidoModel);
    }

    @GetMapping("/{numeroPedido}")
    @ResponseBody
    public PedidoDto findById(@PathVariable Long numeroPedido) {
        return pedidoService.findById(numeroPedido);
    }

    @GetMapping
    @ResponseBody
    public List<PedidoDto> findAll() {
        return pedidoService.findAll();
    }

    @PutMapping("/{numeroPedido}")
    @ResponseBody
    public PedidoDto update(@PathVariable Long numeroPedido, @RequestBody PedidoUpdateDto updateDto) {
        
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
