package br.com.fiap.checkpoint2.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint2.dto.ClienteResponseDTO;
import br.com.fiap.checkpoint2.model.ClienteModel;
import br.com.fiap.checkpoint2.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO save(ClienteModel novoCliente) {
        return modelToDto(clienteRepository.save(novoCliente));
    }

    public ClienteResponseDTO findById(Long codigoCliente) {
        return modelToDto(clienteRepository.findById(codigoCliente).get());
    }

    public List<ClienteResponseDTO> findAll() {

        List<ClienteModel> listModel = clienteRepository.findAll();
        List<ClienteResponseDTO> listDto = new ArrayList<>();

        for(ClienteModel cliente : listModel) {
            listDto.add(modelToDto(cliente));
        }

        return listDto;
    }

    public ClienteResponseDTO update(Long codigoCliente, ClienteModel cliente) {

        Optional<ClienteModel> opClienteUpdate = clienteRepository.findById(codigoCliente);

        if(!opClienteUpdate.isPresent()) {
            throw new RuntimeException("Código: " + codigoCliente + " nao encontrado!");
        }

        ClienteModel clienteUpdate = opClienteUpdate.get();

        clienteUpdate.setNome(cliente.getNome());
        clienteUpdate.setInscricaoFederal(cliente.getInscricaoFederal());
        clienteUpdate.setCep(cliente.getCep());

        return modelToDto(clienteRepository.save(clienteUpdate));
    }

    public void delete(Long codigoCliente) {

        Optional<ClienteModel> opClienteDelete = clienteRepository.findById(codigoCliente);

        if(!opClienteDelete.isPresent()) {
            throw new RuntimeException("Código: " + codigoCliente + " não encontrado!");
        }

        clienteRepository.delete(opClienteDelete.get());
    }

    public ClienteResponseDTO modelToDto(ClienteModel model) {

        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setNome(model.getNome());
        dto.setInscricaoFederal(model.getInscricaoFederal());
        dto.setCep(model.getCep());

        return dto;
    }
}
