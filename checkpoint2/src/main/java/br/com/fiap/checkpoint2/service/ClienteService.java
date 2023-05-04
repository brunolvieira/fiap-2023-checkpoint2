package br.com.fiap.checkpoint2.service;

import java.util.List;
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

    public Boolean existsById(Long codigoCliente) {
        return clienteRepository.existsById(codigoCliente);
    }

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

    public void delete(Long codigoCliente) {
        clienteRepository.delete(clienteRepository.findById(codigoCliente).get());
    }

    private static ClienteResponseDTO modelToDto(ClienteModel model) {

        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.setCodigoCliente(model.getCodigoCliente());
        dto.setNome(model.getNome());
        dto.setInscricaoFederal(model.getInscricaoFederal());
        dto.setCep(model.getCep());

        return dto;
    }
}
