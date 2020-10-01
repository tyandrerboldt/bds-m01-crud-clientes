package com.tyandrerboldt.bdsm01crudclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyandrerboldt.bdsm01crudclientes.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
