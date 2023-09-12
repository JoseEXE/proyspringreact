package com.jose.backend.usersapp.backendusersapp.models.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="etablissement")
public class Etablissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Le nom d'etablissement ne doit pas être vide...")
    private String nom;

    @NotEmpty(message = "Le siret d'etablissement ne doit pas être vide...")
    @Column(name="siret", length=100, nullable=false, unique=true)
    private String siret;

    @NotEmpty(message = "La rue d'etablissement ne doit pas être vide...")
    private String rue;

    @NotEmpty(message = "Le code postal d'etablissement ne doit pas être vide...")
    private String cod_postal;

    @NotEmpty(message = "La ville d'etablissement ne doit pas être vide...")
    private String ville;

    @NotEmpty(message = "Le numéro téléphone d'etablissement ne doit pas être vide...")
    @Column(name="num_tel", length=100, nullable=false, unique=true)
    private String num_tel;

    @CreationTimestamp
    @Column(name= "createdOn", nullable = false, updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime updatedOn;

    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;

    public Etablissement(long id, @NotEmpty(message = "Le nom d'etablissement ne doit pas être vide...") String nom,
            @NotEmpty(message = "Le siret d'etablissement ne doit pas être vide...") String siret,
            @NotEmpty(message = "La rue d'etablissement ne doit pas être vide...") String rue,
            @NotEmpty(message = "Le code postal d'etablissement ne doit pas être vide...") String cod_postal,
            @NotEmpty(message = "La ville d'etablissement ne doit pas être vide...") String ville,
            @NotEmpty(message = "Le numéro téléphone d'etablissement ne doit pas être vide...") String num_tel,
            LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
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

    public Etablissement() {
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
