package com.jose.backend.usersapp.backendusersapp.models.login;



import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.jose.backend.usersapp.backendusersapp.models.entities.User;

public class UserLogin implements UserDetails , CredentialsContainer{

    private User user;
	

	public UserLogin(User user) {
	this.user = user;
	}
	



	/**************FIN******************/
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	public String getFullName() {
		return user.getLastName() + " " + user.getNameU();
	}

    public long getId() {
		return user.getId();
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*   Obtention des Roles   */
        //List<GrantedAuthority> authorities = new ArrayList<>();
        //    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));;
        List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword()
        ;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void eraseCredentials() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eraseCredentials'");
    }


    
}
