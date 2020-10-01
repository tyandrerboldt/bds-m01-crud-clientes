package com.tyandrerboldt.bdsm01crudclientes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyandrerboldt.bdsm01crudclientes.dto.ClientDTO;
import com.tyandrerboldt.bdsm01crudclientes.entities.Client;
import com.tyandrerboldt.bdsm01crudclientes.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		List<Client> clients = clientRepository.findAll();
		List<ClientDTO> clientsDTO = clients.stream()
				.map(client -> new ClientDTO(client))
				.collect(Collectors.toList());
		return clientsDTO;
	}
	
}
