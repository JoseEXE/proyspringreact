package com.jose.backend.usersapp.backendusersapp.models.request;

import jakarta.validation.constraints.NotEmpty;

public class UserRequestModPass {


    @NotEmpty(message = "Vous devez saisir votre mot de passe actuel...")
    private String oldPassword;

    @NotEmpty(message = "Vous devez saisir votre nouveau mot de passe...")
    private String newPassword;
    

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    

    
}
