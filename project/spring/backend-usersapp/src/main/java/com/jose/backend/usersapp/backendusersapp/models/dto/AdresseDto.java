package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.jose.backend.usersapp.backendusersapp.models.entities.Client;



public class AdresseDto {

    private long id;
    private Client client;
    private String rue;
    private String cod_postal;
    private String ville;
    private String complement;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
    private boolean statut;

    public AdresseDto(long id, Client client, String rue, String cod_postal, String ville, String complement,
            LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
        this.id = id;
        this.client = client;
        this.rue = rue;
        this.cod_postal = cod_postal;
        this.ville = ville;
        this.complement = complement;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statut = statut;
    }

    public AdresseDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientDto getClient() {
        ClientDto clientDto = new ClientDto();


        BeanUtils.copyProperties(client,clientDto);

        return clientDto;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    

    

    
}
