package com.jose.backend.usersapp.backendusersapp.models.dto;

public class UserDtoPass {
    private Long id;
    private String email;




    public UserDtoPass(Long id, String email) {
        this.id = id;
        this.email = email;
    
     
    }

    public UserDtoPass() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }




    
    
}
