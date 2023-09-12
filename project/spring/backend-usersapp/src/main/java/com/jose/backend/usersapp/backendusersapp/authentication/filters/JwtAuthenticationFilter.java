package com.jose.backend.usersapp.backendusersapp.authentication.filters;
import static com.jose.backend.usersapp.backendusersapp.authentication.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    public AuthenticationManager authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager ){
       this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

                User user = null;
                String username = null;
                String password = null;


                try {
                    user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                    username = user.getEmail();
                    password = user.getPassword();
                    //id = user.getId();
                    //fullName = user.getLastName() +" "+ user.getNameU();
                
                  //  fullName = user.getNameU() + " " + user.getLastName();

                    //logger.info("fullName: "+fullName);
                    //logger.info("id=========================================================->: "+id);
                    logger.info("username=========================================================->: "+username);
                    logger.info("password=========================================================->: "+password);

                } catch (StreamReadException e) {
                    e.printStackTrace();
                } catch (DatabindException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password);
        System.out.println("authToken: "+authToken);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

                System.out.println("authResult: "+ authResult.getPrincipal());
                System.out.println("getCredentials: "+ authResult.getCredentials());
                System.out.println("getDetails: "+ authResult.getDetails());


                //String usernamex =  ((UserLogin) authResult.getPrincipal()).getFullName();                                 
               
               

               // System.out.println("rs: "+usernamex);

               String usernameT = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
      
                               String userNameDetails = new String(usernameT);
                String[] userNameDetailsT = userNameDetails.split(":");
                String username = userNameDetailsT[0];
                String fullName = userNameDetailsT[1];
                String id = userNameDetailsT[2];

                System.out.println("username: "+username);
                System.out.println("fullName: "+fullName);
                System.out.println("id: "+id);

                //String username = userLogin.getUsername();
                System.out.println("username::::::::: "+authenticationManager);
                /* ROLES */
                Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
                

                boolean isAdmin = roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
                Claims claims = Jwts.claims();
                claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
                claims.put("isAdmin", isAdmin);
                claims.put("id", id );
                claims.put("fullName", fullName );
                /* END ROLES */


                //String originalByte = SECRET_KEY + ":" + username;
                //String token = Base64.getEncoder().encodeToString(originalByte.getBytes()) ;
                String token =Jwts.builder()
                .setClaims(claims) //ROLES
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();


                response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

                Map<String, Object> bodyResponse = new HashMap<>();

                bodyResponse.put("token", token);
                bodyResponse.put("message", String.format("%s avez initialisé la session correctement", fullName));
                bodyResponse.put("username", username);

                response.getWriter().write(new ObjectMapper().writeValueAsString(bodyResponse));
                response.setStatus(200);
                response.setContentType("application/json");

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

                Map<String, Object> bodyResponse = new HashMap<>();
                bodyResponse.put("message", "Erreur d'initialisation de la session");
                bodyResponse.put("error", failed.getMessage());

                response.getWriter().write(new ObjectMapper().writeValueAsString(bodyResponse));
                response.setStatus(401);
                response.setContentType("application/json");



    }
    
    
}
