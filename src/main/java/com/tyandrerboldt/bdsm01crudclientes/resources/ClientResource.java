package com.tyandrerboldt.bdsm01crudclientes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyandrerboldt.bdsm01crudclientes.dto.ClientDTO;
import com.tyandrerboldt.bdsm01crudclientes.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClientDTO> clients = clientService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(clients);
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long clientId){
		ClientDTO clientDTO = clientService.findById(clientId);
		return ResponseEntity.ok().body(clientDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO){
		ClientDTO newClientDTO = clientService.insert(clientDTO);
		return ResponseEntity.ok().body(newClientDTO);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long clientId,@RequestBody ClientDTO clientDTO){
		ClientDTO newClientDTO = clientService.update(clientId, clientDTO);
		return ResponseEntity.ok().body(newClientDTO);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long clientId){
		clientService.delete(clientId);
		return ResponseEntity.noContent().build();
	}
	
}
