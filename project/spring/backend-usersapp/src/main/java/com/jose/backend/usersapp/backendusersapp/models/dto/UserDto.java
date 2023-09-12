package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.util.List;

import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;
import com.jose.backend.usersapp.backendusersapp.models.interfaces.IUser;

public class UserDto implements IUser{
    private Long id;
    private String nameU;
    private String lastName;
    private String email;
    private boolean admin;
    private boolean statut;
    private List<Etablissement> etablissements ;

    

    /* CONSTRUCTORS */

    public UserDto(Long id, String nameU, String lastName, String email, boolean Admin) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.admin = Admin;
    }

    
    public UserDto(Long id, String nameU, String lastName, String email, boolean admin, boolean statut) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
        this.statut = statut;
    }


    

    public UserDto(Long id, String nameU, String lastName, String email, boolean admin, boolean statut,
            List<Etablissement> etablissements) {
        this.id = id;
        this.nameU = nameU;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
        this.statut = statut;
        this.etablissements = etablissements;
    }


    public UserDto() {
    }
    
    /* GETTERS AND SETTERS */

    public Long getId() {
        return id;
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


    

}
