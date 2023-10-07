package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.time.LocalDateTime;


public class ClientDto {

    private long id;
    private String nom;
    private String prenom;
    private String tel;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
    private boolean statut;
    public ClientDto(long id, String nom, String prenom, String tel, LocalDateTime createdOn,
            LocalDateTime updatedOn, boolean statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statut = statut;
    }
    
    public ClientDto() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
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
