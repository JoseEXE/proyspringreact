package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;


import com.jose.backend.usersapp.backendusersapp.models.dto.UserDtoPass;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;

public class DtoMapperUserPass {
    private User user;

    private DtoMapperUserPass(){
        
    }

    public static DtoMapperUserPass builder(){

        return new DtoMapperUserPass();
    }

    public DtoMapperUserPass setUserPass(User user){
        this.user =user;
        return this;
    }

    public UserDtoPass build(){
        if(user == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

         return new UserDtoPass(this.user.getId(), user.getEmail());
    }
    
}
