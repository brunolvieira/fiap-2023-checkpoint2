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

import br.com.fiap.checkpoint2.dto.ClienteCreateDTO;
import br.com.fiap.checkpoint2.dto.ClienteResponseDTO;
import br.com.fiap.checkpoint2.dto.ClienteUpdateDTO;
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
    public ClienteResponseDTO save(@RequestBody ClienteCreateDTO clienteDto) {

        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setNome(clienteDto.getNome());
        clienteModel.setInscricaoFederal(clienteDto.getInscricaoFederal());
        clienteModel.setCep(clienteDto.getCep());

        return clienteService.save(clienteModel);
    }

    @GetMapping("/{codigoCliente}")
    @ResponseBody
    public ClienteResponseDTO findById(@PathVariable Long codigoCliente) {
        return clienteService.findById(codigoCliente);
    }

    @GetMapping
    @ResponseBody
    public List<ClienteResponseDTO> findAll() {
        return clienteService.findAll();
    }

    @PutMapping("/{codigoCliente}")
    @ResponseBody
    public ClienteResponseDTO update(@PathVariable Long codigoCliente, @RequestBody ClienteUpdateDTO clienteUpdate) {

        Boolean clienteExiste = clienteService.existsById(codigoCliente);

        if(!clienteExiste) {
            throw new RuntimeException("Cliente " + codigoCliente + " não encontrado!");
        }

        ClienteModel cliente = new ClienteModel();

        cliente.setCodigoCliente(codigoCliente);
        cliente.setNome(clienteUpdate.getNome());
        cliente.setInscricaoFederal(clienteUpdate.getInscricaoFederal());
        cliente.setCep(clienteUpdate.getCep());

        return clienteService.save(cliente);
    }

    @DeleteMapping("/{codigoCliente}")
    public void delete(@PathVariable Long codigoCliente) {

        Boolean clienteExiste = clienteService.existsById(codigoCliente);

        if(!clienteExiste) {
            throw new RuntimeException("Cliente " + codigoCliente + " não encontrado!");
        }
        
        clienteService.delete(codigoCliente);
    }
}
