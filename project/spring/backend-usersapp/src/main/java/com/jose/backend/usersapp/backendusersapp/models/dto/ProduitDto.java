package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.time.LocalDateTime;





public class ProduitDto {

    private long id;
    private Long catProduit;
    private Long user;
    private String code;
    private String nom;
    private String description;
    private Double prix;
    private String type_plat;
    private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
    private boolean statut;

    public ProduitDto(long id, Long catProduit, Long user, String code, String nom, String description, Double prix,
            String type_plat, LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
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
    
    public ProduitDto(long id, Long catProduit, Long user, String code, String nom, String description, Double prix,
            String type_plat, boolean statut) {
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

    public ProduitDto(Long catProduit, Long user, String code, String nom, String description, Double prix,
            String type_plat, boolean statut) {
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
        this.statut = statut;
    }

        public ProduitDto(Long catProduit, Long user, String code, String nom, String description, Double prix,
            String type_plat) {
        this.catProduit = catProduit;
        this.user = user;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.type_plat = type_plat;
    }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Long getCatProduit() {
            return catProduit;
        }

        public void setCatProduit(Long catProduit) {
            this.catProduit = catProduit;
        }

        public Long getUser() {
            return user;
        }

        public void setUser(Long user) {
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
