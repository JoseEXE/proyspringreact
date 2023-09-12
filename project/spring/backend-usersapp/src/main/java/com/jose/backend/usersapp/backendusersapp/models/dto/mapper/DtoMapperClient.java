package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.ClientDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;

public class DtoMapperClient {

    private Client client;

    private DtoMapperClient(){
    }

    public static DtoMapperClient builder(){
        return new DtoMapperClient();
    }

    /* CONSTRUCTOR */
    public  DtoMapperClient setClient(Client client){
        this.client = client;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public ClientDto build(){
        if(client == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

        return new ClientDto(
            this.client.getId(),
            this.client.getNom(),
            this.client.getPrenom(),
            this.client.getNum_tel(),
            this.client.getCreatedOn(),
            this.client.getUpdatedOn(),
            this.client.isStatut());

    }

    
}
