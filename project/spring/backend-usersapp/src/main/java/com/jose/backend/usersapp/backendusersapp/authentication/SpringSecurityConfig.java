package com.jose.backend.usersapp.backendusersapp.authentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jose.backend.usersapp.backendusersapp.authentication.filters.JwtAuthenticationFilter;
import com.jose.backend.usersapp.backendusersapp.authentication.filters.JwtValidationFilter;
import com.jose.backend.usersapp.backendusersapp.services.JpaUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
 
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
	public UserDetailsService userDetailsService() {
	    return new JpaUserDetailsService();
	}
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder()); 
	    return authProvider;
	}
    @Bean 
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain filterChain( HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/users", "/users/page/{page}",
                                                        "/catproduits", "/catproduits/page/{page}", 
                                                        "/produits", "/produits/page/{page}",
                                                        "/etablissements", "/etablissements/page/{page}",
                                                        "/clients", "/clients/page/{page}",
                                                        "/adresses", "/adresses/page/{page}",
                                                        "/commandes", "/commandes/page/{page}",
                                                        "/detailcommandes", "/detailcommandes/page/{page}").permitAll()
            .requestMatchers(HttpMethod.GET, "/users/{id}", "/catproduits/{id}", "/produits/{id}",
                                                         "/etablissements/{id}", "/clients/{id}", "/adresses/{id}",
                                                         "/commandes/{id}", "/detailcommandes/{id}").hasAnyRole("USER","ADMIN")
            .requestMatchers(HttpMethod.PUT, "/catproduits/{id}", "/produits/{id}", "/etablissements/{id}",
                                                        "/clients/{id}", "/adresses/{id}", "/commandes/{id}",
                                                        "/detailcommandes/{id}").hasAnyRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/users/{id}","/users/pass/{id}").permitAll()
            .requestMatchers("/users/{id}","/users/pass/{id}").permitAll()
            .requestMatchers(HttpMethod.POST, "/users", "/catproduits", "/produits",
                                                        "/etablissements", "/clients", "/adresses",
                                                        "/commandes", "/detailcommandes").hasRole("ADMIN")
            .requestMatchers("/users/**", "/users/pass/**", "/catproduits/**", "/produits/**",
                                                        "/etablissements/**", "/clients/**", "/adresses/**",
                                                        "/commandes/**", "/detailcommandes/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/users/pass/**", "/clients/**", "/adresses/**", "/commandes/**", "/detailcommandes/**").hasRole("USER")
            .requestMatchers(HttpMethod.POST, "/users/pass/**", "/clients/**", "/adresses/**", "/commandes/**", "/detailcommandes/**").hasRole("USER")
            //.requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")
            //.requestMatchers(HttpMethod.PUT, "/users/{id}").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
            .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
            .csrf(config -> config.disable())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Config avent login react
            .build();
    }

    /* Config avent login react */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 

        source.registerCorsConfiguration("/**", config);

        return source;
    }
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

    /* END Config avent login react */
    
}
