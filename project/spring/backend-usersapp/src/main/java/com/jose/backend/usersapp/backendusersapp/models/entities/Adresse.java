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
@Table(name="adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn(name="id_client")
    private Client client;

    @NotEmpty(message = "La rue de l'adresse ne doit pas être vide...")
    private String rue;

    
    @NotEmpty(message = "Le code_postal de l'adresse ne doit pas être vide...")
    private String cod_postal;

    @NotEmpty(message = "La ville de l'adresse ne doit pas être vide...")
    private String ville;

    private String complement;

    @CreationTimestamp
	private LocalDateTime createdOn;
	@UpdateTimestamp
	private LocalDateTime updatedOn;

    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;

    public Adresse(long id, Client client,
            @NotEmpty(message = "La rue de l'adresse ne doit pas être vide...") String rue,
            @NotEmpty(message = "Le code_postal de l'adresse ne doit pas être vide...") String cod_postal,
            @NotEmpty(message = "La ville de l'adresse ne doit pas être vide...") String ville, String complement,
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

    public Adresse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
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
