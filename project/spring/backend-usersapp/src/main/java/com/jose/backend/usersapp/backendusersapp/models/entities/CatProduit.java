package com.jose.backend.usersapp.backendusersapp.models.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "cat_produit")
public class CatProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotEmpty(message = "Le nom de categorie produit ne doit pas être vide...")
    @Column(name="nom", length=100, nullable=false, unique=true )
    private String nom;

    @NotEmpty(message = "Le description de categorie ne doit pas être vide...")
    private String description;

    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;
    
    public CatProduit(long id, @NotEmpty(message = "Le nom de categorie produit ne doit pas être vide...") String nom,
            @NotEmpty(message = "Le description de categorie ne doit pas être vide...") String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public CatProduit(long id) {
        this.id = id;
    }

    
    public CatProduit(long id, @NotEmpty(message = "Le nom de categorie produit ne doit pas être vide...") String nom,
            @NotEmpty(message = "Le description de categorie ne doit pas être vide...") String description,
            boolean statut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statut = statut;
    }



    public CatProduit() {
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
