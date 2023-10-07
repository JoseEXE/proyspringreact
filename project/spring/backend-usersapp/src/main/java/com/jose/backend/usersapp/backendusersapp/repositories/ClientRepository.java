package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findById(Long id);
    // Optional<Client> findBytel(String tel);

    List<Client> findByTelContaining(String tel);
    
    //Pagination
    Page<Client> findAll(Pageable pageable);
}




