package com.jose.backend.usersapp.backendusersapp.authentication.filters;
import static com.jose.backend.usersapp.backendusersapp.authentication.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.backend.usersapp.backendusersapp.authentication.SimpleGrantedAuthorityMixInJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
      
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain)
            throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);

        String header = request.getHeader(HEADER_AUTHORIZATION);
        //System.out.println("=================>header: "+ header);

        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN,"");
    /* 
        byte[] tokenDecodeBytes = Base64.getDecoder().decode(token);
        String tokenDecode = new String(tokenDecodeBytes);
        String[] tokenArr = tokenDecode.split(":");
        String secret = tokenArr[0];
        String username = tokenArr[1];
    */
        //System.out.println("===========================>secret: "+secret);
        //System.out.println("===========================>username: "+username);

        //if(SECRET_KEY.equals(secret)){
        try{

            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
            .parseClaimsJws(token).getBody();

            Object authoritiesClaims = claims.get("authorities");
            String username = claims.getSubject(); 


            //List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixInJsonCreator.class)
            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }catch(JwtException e){

            Map<String, String> body = new HashMap<>();
            body.put("message","Le token JWT n'est pas valide JwtValidationFilter!!! ");
            body.put("error", e.getMessage());
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(401);
            response.setContentType("application/json");

        }

    }
    
}
