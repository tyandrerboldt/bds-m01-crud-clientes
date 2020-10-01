package com.tyandrerboldt.bdsm01crudclientes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyandrerboldt.bdsm01crudclientes.dto.ClientDTO;
import com.tyandrerboldt.bdsm01crudclientes.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<ClientDTO> clients = clientService.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
}
