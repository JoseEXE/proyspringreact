package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse, Long>{
    
    Optional<Adresse> findById(Long id);

    Page<Adresse> findAll(Pageable pageable);
}
