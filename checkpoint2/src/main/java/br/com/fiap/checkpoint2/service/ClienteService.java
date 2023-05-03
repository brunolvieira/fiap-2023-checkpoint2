package br.com.fiap.checkpoint2.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ClienteDto;
import br.com.fiap.checkpoint2.model.ClienteModel;
import br.com.fiap.checkpoint2.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto save(ClienteModel clienteModel) {
        return modelToDto(clienteRepository.save(clienteModel));
    }

    public ClienteDto findById(Long codigoCliente) {
        return modelToDto(clienteRepository.findById(codigoCliente).get());
    }

    public List<ClienteDto> findAll() {

        List<ClienteModel>listModel = clienteRepository.findAll();
        List<ClienteDto> listDto = new ArrayList<>();

        for(ClienteModel cliente : listModel) {
            listDto.add(modelToDto(cliente));
        }

        return listDto;
    }

    public ClienteDto update(Long codigoCliente, ClienteModel clienteModel) {

        Optional<ClienteModel> opClienteUpdate = clienteRepository.findById(codigoCliente);

        if(!opClienteUpdate.isPresent()) {
            throw new RuntimeException("Código: " + codigoCliente + " nao encontrado!");
        }

        ClienteModel clienteUpdate = opClienteUpdate.get();

        clienteUpdate.setNome(clienteModel.getNome());
        clienteUpdate.setInscricaoFederal(clienteModel.getInscricaoFederal());
        clienteUpdate.setCep(clienteModel.getCep());

        return modelToDto(clienteRepository.save(clienteUpdate));
    }

    public void delete(Long codigoCliente) {

        Optional<ClienteModel> opClienteDelete = clienteRepository.findById(codigoCliente);

        if(!opClienteDelete.isPresent()) {
            throw new RuntimeException("Código: " + codigoCliente + " nao encontrado!");
        }

        ClienteModel clienteDelete = opClienteDelete.get();

        clienteRepository.delete(clienteDelete);
    }

    public ClienteDto modelToDto(ClienteModel model) {

        ClienteDto dto = new ClienteDto();

        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setNome(model.getNome());
        dto.setInscricaoFederal(model.getInscricaoFederal());
        dto.setCep(model.getCep());

        return dto;
    }
}
