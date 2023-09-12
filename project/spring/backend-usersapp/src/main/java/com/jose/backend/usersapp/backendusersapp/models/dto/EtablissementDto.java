package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.time.LocalDateTime;

public class EtablissementDto {

    private long id;
    private String nom;
    private String siret;
    private String rue;
    private String cod_postal;
    private String ville;
    private String num_tel;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private boolean statut;
    
    public EtablissementDto(long id, String nom, String siret, String rue, String cod_postal, String ville,
            String num_tel, LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
        this.id = id;
        this.nom = nom;
        this.siret = siret;
        this.rue = rue;
        this.cod_postal = cod_postal;
        this.ville = ville;
        this.num_tel = num_tel;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statut = statut;
    }
    public EtablissementDto() {
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
    public String getSiret() {
        return siret;
    }
    public void setSiret(String siret) {
        this.siret = siret;
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
    public String getNum_tel() {
        return num_tel;
    }
    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
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
