package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;


public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Optional<Commande> findById(Long id);

    Page<Commande>  findAll(Pageable pageable);
}
