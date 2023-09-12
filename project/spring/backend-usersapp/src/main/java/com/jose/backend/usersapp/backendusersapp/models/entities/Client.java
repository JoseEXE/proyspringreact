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
@Table(name = "client")
public class Client {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Le nom de client ne doit pas être vide...")
    private String nom;

    @NotEmpty(message = "Le prenom de client ne doit pas être vide...")
    private String prenom;

    @NotEmpty(message = "Le num telephonoque de client ne doit pas être vide...")
    @Column(name="num_tel", length=100, nullable=false, unique=true )
    private String num_tel;

    @CreationTimestamp
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime updatedOn;

    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;

    public Client(long id, @NotEmpty(message = "Le nom de client ne doit pas être vide...") String nom,
            @NotEmpty(message = "Le prenom de client ne doit pas être vide...") String prenom,
            @NotEmpty(message = "Le num telephonoque de client ne doit pas être vide...") String num_tel,
            LocalDateTime createdOn, LocalDateTime updatedOn, boolean statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statut = statut;
    }

    public Client() {
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
