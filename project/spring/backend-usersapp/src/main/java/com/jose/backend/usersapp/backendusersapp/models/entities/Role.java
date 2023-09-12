package com.jose.backend.usersapp.backendusersapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    /* GETTERS AND SETTERS */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /* CONSTRUCTOR */
    
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }





}
