package com.capgemini.sprint1.Sprint1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.sprint1.Sprint1.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
