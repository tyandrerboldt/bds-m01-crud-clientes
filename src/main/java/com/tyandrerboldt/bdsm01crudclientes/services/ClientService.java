package com.tyandrerboldt.bdsm01crudclientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> clients = clientRepository.findAll(pageRequest);		
		return clients.map(client -> new ClientDTO(client));
	}
	
}
