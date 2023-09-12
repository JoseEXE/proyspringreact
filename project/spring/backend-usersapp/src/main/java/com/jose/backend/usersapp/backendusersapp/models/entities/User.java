package com.jose.backend.usersapp.backendusersapp.models.entities;


import java.util.List;

import com.jose.backend.usersapp.backendusersapp.models.interfaces.IUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="user")
public class User implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Le nom ne doit pas être vide...")
    @Size(min = 2, max = 12, message = "Le prenom doit contenir entre 2 et 12 characters...")
    private String nameU;

    @NotEmpty(message = "Le prenom ne doit pas être vide...")
    private String lastName;

    @NotEmpty
    @Email
    @Column(name="email", length=100, nullable=false, unique=true)
    private String email;
    
    @NotEmpty
    private String password;

    @Transient //jakarta.persistence
    private boolean admin;
    
    @Column(name = "statut", columnDefinition = "boolean default true")
    private boolean statut;
    
//(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles ;
//(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(
        name = "user_etablissement",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="etablissement_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "etablissement_id"})})
    private List<Etablissement> etablissements ;
    


    /** Getters and Setters **/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User(Long id, @NotBlank @Size(min = 4, max = 12) String nameU,
            @NotEmpty(message = "Le prenom ne doit pas être vide...") String lastName, @NotEmpty @Email String email,
            @NotEmpty String password, boolean admin, List<Role> roles) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.roles = roles;
    }

      public User() {
    }

    public User(Long id,
            @NotEmpty(message = "Le nom ne doit pas être vide...") @Size(min = 2, max = 12, message = "Le prenom doit contenir entre 2 et 12 characters...") String nameU,
            @NotEmpty(message = "Le prenom ne doit pas être vide...") String lastName, @NotEmpty @Email String email,
            @NotEmpty String password, boolean admin, boolean statut, List<Role> roles) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.statut = statut;
        this.roles = roles;
    }

    

    public User(Long id,
            @NotEmpty(message = "Le nom ne doit pas être vide...") @Size(min = 2, max = 12, message = "Le prenom doit contenir entre 2 et 12 characters...") String nameU,
            @NotEmpty(message = "Le prenom ne doit pas être vide...") String lastName, @NotEmpty @Email String email,
            @NotEmpty String password, boolean admin, boolean statut, List<Role> roles,
            List<Etablissement> etablissements) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.statut = statut;
        this.roles = roles;
        this.etablissements = etablissements;
    }
    


    @Override
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    
    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

 
    
}
