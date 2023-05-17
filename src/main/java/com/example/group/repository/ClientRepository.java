package com.example.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.group.Entity.Client;

@RepositoryRestResource(path =  "client")
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
