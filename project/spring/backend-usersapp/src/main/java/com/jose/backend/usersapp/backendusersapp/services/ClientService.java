package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jose.backend.usersapp.backendusersapp.models.dto.ClientDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;


public interface ClientService {
    
    List<ClientDto> findAll();

    Page<ClientDto> findAll(Pageable pageable);

    Optional<ClientDto> findById(Long id);

    ClientDto save(Client client);

    Optional<ClientDto> update(Client client, Long id);

    void remove(Long id);

}
