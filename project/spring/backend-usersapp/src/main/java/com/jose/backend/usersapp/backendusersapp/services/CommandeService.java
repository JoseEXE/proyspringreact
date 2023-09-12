package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.CommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;

public interface CommandeService {
    
    List<CommandeDto> findAll();

    Page<CommandeDto> findAll(Pageable pageable);

    Optional<CommandeDto> findById(Long id);

    CommandeDto save(Commande commande);

    Optional<CommandeDto> update(Commande commande, Long id);

    void remove(Long id);

}
