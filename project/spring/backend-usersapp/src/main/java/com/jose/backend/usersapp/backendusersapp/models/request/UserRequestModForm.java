package com.jose.backend.usersapp.backendusersapp.models.request;

import com.jose.backend.usersapp.backendusersapp.models.interfaces.IUser;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRequestModForm implements IUser {
    
    @NotBlank
    @Size(min = 4, max = 12)
    private String nameU;

    @NotEmpty(message = "Le prenom ne doit pas être vide...")
    private String lastName;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    private boolean admin;


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
    
}
