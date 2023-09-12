package com.jose.backend.usersapp.backendusersapp.models.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;



public class CommandeDto {

    private long id;
    private Client client;
    private User user;
    private String commentaire;
    private Double totalHl;
    private Double total;
    private String type_paiement;
    private String etat;
    private LocalDateTime createdOn;
    
    public CommandeDto(long id, Client client, User user, String commentaire, Double totalHl, Double total,
            String type_paiement, String etat, LocalDateTime createdOn) {
        this.id = id;
        this.client = client;
        this.user = user;
        this.commentaire = commentaire;
        this.totalHl = totalHl;
        this.total = total;
        this.type_paiement = type_paiement;
        this.etat = etat;
        this.createdOn = createdOn;
    }
    public CommandeDto() {
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
    public UserDto getUser() {
        UserDto userDto = new UserDto();
        boolean isAdmin = user.getRoles().stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));
        user.setAdmin(isAdmin);
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public Double getTotalHl() {
        return totalHl;
    }
    public void setTotalHl(Double totalHl) {
        this.totalHl = totalHl;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getType_paiement() {
        return type_paiement;
    }
    public void setType_paiement(String type_paiement) {
        this.type_paiement = type_paiement;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    

    
}
