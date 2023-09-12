package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

    Optional<Etablissement> findById(Long id);
    
    //Pagination
    Page<Etablissement> findAll(Pageable pageable);
    
}
