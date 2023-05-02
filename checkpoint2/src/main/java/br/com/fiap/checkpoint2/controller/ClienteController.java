package br.com.fiap.checkpoint2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint2.dto.ClienteCreateDto;
import br.com.fiap.checkpoint2.dto.ClienteDto;
import br.com.fiap.checkpoint2.dto.ClienteUpdateDto;
import br.com.fiap.checkpoint2.model.ClienteModel;
import br.com.fiap.checkpoint2.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Manutenção de informações sobre os Clientes.")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseBody
    public ClienteDto save(@RequestBody ClienteCreateDto clienteDto) {

        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setNome(clienteDto.getNome());
        clienteModel.setInscricaoFederal(clienteDto.getInscricaoFederal());
        clienteModel.setCep(clienteDto.getCep());

        return clienteService.save(clienteModel);
    }

    @GetMapping("/{codigoCliente}")
    @ResponseBody
    public ClienteDto findById(@PathVariable Long codigoCliente) {
        return clienteService.findById(codigoCliente);
    }

    @GetMapping
    @ResponseBody
    public List<ClienteDto> findAll() {
        return clienteService.findAll();
    }

    @PutMapping("/{codigoCliente}")
    @ResponseBody
    public ClienteDto update(@PathVariable Long codigoCliente, @RequestBody ClienteUpdateDto updateDto) {

        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setNome(updateDto.getNome());
        clienteModel.setInscricaoFederal(updateDto.getInscricaoFederal());
        clienteModel.setCep(updateDto.getCep());

        return clienteService.update(codigoCliente, clienteModel);
    }

    @DeleteMapping("/{codigoCliente}")
    public void delete(@PathVariable Long codigoCliente) {
        clienteService.delete(codigoCliente);
    }
}
