package com.jose.backend.usersapp.backendusersapp.models.dto;


public class CatProduitDto {
    
    private long id;
    private String nom;
    private String description;
    private boolean statut;
    

    public CatProduitDto(long id) {
        this.id = id;
    }

    public CatProduitDto(long id, String nom, String description, boolean statut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statut = statut;
    }
    
    public CatProduitDto(long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    public CatProduitDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isStatut() {
        return statut;
    }
    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    


    

}
