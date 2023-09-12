package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.DetailCommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.DetailCommande;

public interface DetailCommandeService {
    
    List<DetailCommandeDto> findAll();

    Page<DetailCommandeDto> findAll(Pageable pageable);

    Optional<DetailCommandeDto> findById(Long id);

    DetailCommandeDto save(DetailCommande detailCommande);

    Optional<DetailCommandeDto> update(DetailCommande detailCommande, Long id);

    void remove(Long id);
}
