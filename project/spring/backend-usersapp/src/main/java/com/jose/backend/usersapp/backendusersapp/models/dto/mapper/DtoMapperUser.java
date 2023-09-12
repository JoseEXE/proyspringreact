package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;

public class DtoMapperUser {


    //private static DtoMapperUser mapper;
    private User user;
    private DtoMapperUser(){

    }

    public static DtoMapperUser builder(){
        //mapper = new DtoMapperUser();
        //return mapper;
        return new DtoMapperUser();
    }


    /* CONTRUCTOR */
    public DtoMapperUser setUser(User user) {
        this.user = user;
        //return mapper;
        return this;
    }

    public UserDto build() {
        if(user == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }
        
        boolean isAdmin = user.getRoles().stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));

        return new UserDto(this.user.getId(), user.getNameU(), user.getLastName(), user.getEmail(), isAdmin, user.isStatut());

    }

    
}
