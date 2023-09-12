package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.DetailCommande;

public interface DetailCommandeRepository extends JpaRepository<DetailCommande, Long> {

    Optional<DetailCommande> findById(Long id);

    Page<DetailCommande> findAll(Pageable pageable);
    
}
