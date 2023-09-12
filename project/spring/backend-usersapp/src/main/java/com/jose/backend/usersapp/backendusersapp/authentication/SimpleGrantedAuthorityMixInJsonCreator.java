package com.jose.backend.usersapp.backendusersapp.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixInJsonCreator {
    
    @JsonCreator
    public SimpleGrantedAuthorityMixInJsonCreator(@JsonProperty("authority") String role){

    }
}
