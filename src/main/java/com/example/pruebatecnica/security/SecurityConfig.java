package com.example.pruebatecnica.security;


import com.example.pruebatecnica.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter JwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(
               authRequest -> authRequest.requestMatchers("/api/auth/**").permitAll()
                       .anyRequest().authenticated()
       )
       .sessionManagement(sessionManager ->
               sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .authenticationProvider(authProvider)
       .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
       .build();
   }



}
