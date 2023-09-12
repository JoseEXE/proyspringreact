package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.ProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;

public interface ProduitService {
    
    List<ProduitDto> findAll();

    Page<ProduitDto> findAll(Pageable pageable);

    Optional<ProduitDto> findById(Long id);

    ProduitDto save(Produit produit);

    Optional<ProduitDto> update(Produit produit, Long id);

    void remove(Long id);


}
