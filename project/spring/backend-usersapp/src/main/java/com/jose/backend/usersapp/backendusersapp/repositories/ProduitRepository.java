package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
    Optional<Produit> findById(Long id);

    //Pagination
    Page<Produit> findAll(Pageable pageable);
    
}
