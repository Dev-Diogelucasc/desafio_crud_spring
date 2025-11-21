package com.devdioge.desafioCrud.service;

import com.devdioge.desafioCrud.dto.ClientDto;
import com.devdioge.desafioCrud.entity.ClientEntity;
import com.devdioge.desafioCrud.repository.ClientRepository;
import com.devdioge.desafioCrud.service.exception.DataBaseException;
import com.devdioge.desafioCrud.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        List<ClientEntity> result = clientRepository.findAll();
        return result.stream().map(clientEntity -> modelMapper.map(clientEntity, ClientDto.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontado"));
        return modelMapper.map(client, ClientDto.class);
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        ClientEntity client = modelMapper.map(dto, ClientEntity.class);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);

    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        try {
            ClientEntity client = clientRepository.getReferenceById(id);
            client.setName(dto.getName());
            client.setCpf(dto.getCpf());
            client.setIncome(dto.getIncome());
            client.setBirthDate(dto.getBirthDate());
            client.setChildren(dto.getChildren());
            clientRepository.save(client);
            return modelMapper.map(client, ClientDto.class);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!clientRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Cliente não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

}
