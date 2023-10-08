package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.List;
import java.util.Optional;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;

public interface AdresseRepository extends JpaRepository<Adresse, Long>{
    
    Optional<Adresse> findById(Long id);
    
    List<Adresse> findByClient(Client client);

    Page<Adresse> findAll(Pageable pageable);
}
