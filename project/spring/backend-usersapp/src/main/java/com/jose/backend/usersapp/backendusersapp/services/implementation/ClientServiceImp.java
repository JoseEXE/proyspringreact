package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.ClientDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperClient;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import com.jose.backend.usersapp.backendusersapp.repositories.ClientRepository;
import com.jose.backend.usersapp.backendusersapp.services.ClientService;

@Service
public class ClientServiceImp implements ClientService{
    
    @Autowired
    private ClientRepository clientRepository;
  /*=======================================================*/
    /*1- CREATE Client  */
    @Override
    @Transactional
    public ClientDto save(Client client) {
        return DtoMapperClient.builder().setClient(clientRepository.save(client)).build();
    }
     /*1- END CREATE Client  */
    /*=======================================================*/
    /* 2.1- READ Client  */
    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        return clients
                    .stream()
                    .map(u -> DtoMapperClient.builder().setClient(u).build())
                    .collect(Collectors.toList());  
    }
    /* 2.1- END READ Client  */
    /*=======================================================*/
    /* 2.2- READ Client pageable */
    @Override
    @Transactional
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(u -> DtoMapperClient.builder().setClient(u).build());
    }
    /* 2.2- END READ Client pageable */
    /*=======================================================*/
    /*3- UPDATE Client  */
    @Override
    @Transactional
    public Optional<ClientDto> update(Client client, Long id) {
        Optional<Client> o = clientRepository.findById(id);
        Client clientOptional = null;
        if(o.isPresent()){
            Client clientDb = o.orElseThrow();

            clientDb.setNom(client.getNom());
            clientDb.setPrenom(client.getPrenom());
            clientDb.setNum_tel(client.getNum_tel());
            clientDb.setStatut(client.isStatut());
            clientOptional = clientRepository.save(clientDb);
        }
        return Optional.ofNullable(DtoMapperClient.builder().setClient(clientOptional).build());
    }
    /*3- END UPDATE Client  */
    /*=======================================================*/
    /*4- DELETE Client  */
    @Override
    @Transactional
    public void remove(Long id) {
        clientRepository.deleteById(id);
    }
    /*4- END DELETE Client  */
    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/

    /*5- READ BY ID Client  */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDto> findById(Long id) {
        return clientRepository.findById(id).map(u -> DtoMapperClient.builder().setClient(u).build());

    }
    /*5- END READ BY ID Client  */












    
}
