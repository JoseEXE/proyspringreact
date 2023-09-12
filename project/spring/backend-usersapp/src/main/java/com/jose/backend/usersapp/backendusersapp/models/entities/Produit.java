package com.jose.backend.usersapp.backendusersapp.models.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn(name="id_cat_produit")
    private CatProduit catProduit;
    
    @ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn(name="id_user")
    private User user;

    @NotEmpty(message = "Le code produit ne doit pas être vide...")
    private String code;
    @NotEmpty(message = "Le nom produit ne doit pas être vide...")
    private String nom;

    private String description;
    @NotEmpty(message = "Le prix produit ne doit pas être vide...")
    private Double prix;

    @NotEmpty(message = "Le type de plat (Chaud, froid) produit ne doit pas être vide...")
    private String type_plat;
    @CreationTimestamp
	private LocalDateTime createdOn;
	@UpdateTimestamp
	private LocalDateTime updatedOn;

    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;

    
    public Produit(long id, CatProduit catProduit, User user,
            @NotEmpty(message = "Le code produit ne doit pas être vide...") String code,
            @NotEmpty(message = "Le nom produit ne doit pas être vide...") String nom, String description,
            @NotEmpty(message = "Le prix produit ne doit pas être vide...") Double prix,
            @NotEmpty(message = "Le type de plat (Chaud, froid) produit ne doit pas être vide...") String type_plat,
            LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.id = id;
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }


    public Produit(long id, CatProduit catProduit, User user,
            @NotEmpty(message = "Le code produit ne doit pas être vide...") String code,
            @NotEmpty(message = "Le nom produit ne doit pas être vide...") String nom, String description,
            @NotEmpty(message = "Le prix produit ne doit pas être vide...") Double prix,
            @NotEmpty(message = "Le type de plat (Chaud, froid) produit ne doit pas être vide...") String type_plat) {
        this.id = id;
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
    }




    public Produit(long id, CatProduit catProduit, User user,
            @NotEmpty(message = "Le code produit ne doit pas être vide...") String code,
            @NotEmpty(message = "Le nom produit ne doit pas être vide...") String nom, String description,
            @NotEmpty(message = "Le prix produit ne doit pas être vide...") Double prix,
            @NotEmpty(message = "Le type de plat (Chaud, froid) produit ne doit pas être vide...") String type_plat,
            boolean statut) {
        this.id = id;
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
        this.statut = statut;
    }


    public Produit(long id, CatProduit catProduit, User user,
            @NotEmpty(message = "Le code produit ne doit pas être vide...") String code,
            @NotEmpty(message = "Le nom produit ne doit pas être vide...") String nom, String description,
            @NotEmpty(message = "Le prix produit ne doit pas être vide...") Double prix,
            @NotEmpty(message = "Le type de plat (Chaud, froid) produit ne doit pas être vide...") String type_plat,
            LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
        this.id = id;
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statut = statut;
    }


    public Produit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CatProduit getCatProduit() {
        return catProduit;
    }


    public void setCatProduit(CatProduit catProduit) {
        this.catProduit = catProduit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getType_plat() {
        return type_plat;
    }

    public void setType_plat(String type_plat) {
        this.type_plat = type_plat;
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
