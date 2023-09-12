package com.jose.backend.usersapp.backendusersapp.models.dto;

import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;

public class DetailCommandeDto {
    
    private long id;
    private Commande commande;
    private Produit produit;
    private int quantite;
    private Double prix_unitaire;

    public DetailCommandeDto(long id, Commande commande, Produit produit, int quantite, Double prix_unitaire) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
    }
    public DetailCommandeDto() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Commande getCommande() {
        return commande;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Double getPrix_unitaire() {
        return prix_unitaire;
    }
    public void setPrix_unitaire(Double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    
    

}
