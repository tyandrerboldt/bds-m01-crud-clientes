package com.tyandrerboldt.bdsm01crudclientes.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyandrerboldt.bdsm01crudclientes.dto.ClientDTO;
import com.tyandrerboldt.bdsm01crudclientes.entities.Client;
import com.tyandrerboldt.bdsm01crudclientes.repositories.ClientRepository;
import com.tyandrerboldt.bdsm01crudclientes.services.exceptions.DatabaseException;
import com.tyandrerboldt.bdsm01crudclientes.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> clients = clientRepository.findAll(pageRequest);		
		return clients.map(client -> new ClientDTO(client));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {		
		Client client = new Client();		
		copyDtoToEntity(clientDTO, client);		
		client = clientRepository.save(client);		
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long clientId, ClientDTO clientDTO) {
		try {
			Client client = clientRepository.getOne(clientId);
			copyDtoToEntity(clientDTO, client);
			client = clientRepository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + clientId);
		}		
	}
	
	public void delete(Long clientId) {
		try {
			clientRepository.deleteById(clientId);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + clientId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(ClientDTO clientDTO, Client client) {
		client.setName(clientDTO.getName());
		client.setCpf(clientDTO.getCpf());
		client.setIncome(clientDTO.getIncome());
		client.setBirthDate(clientDTO.getBirthDate());
		client.setChildren(clientDTO.getChildren());
	}
	
}
