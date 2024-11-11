/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.MedQuery.security;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author hugok
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { 
        return httpSecurity 
            .csrf(csrf -> csrf.disable()) 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authorizeHttpRequests(authorize -> authorize 
                .requestMatchers(HttpMethod.POST, "/auth/index").permitAll()
                .requestMatchers(HttpMethod.GET, "/auth/index").permitAll() 
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() 
                .anyRequest().permitAll()
                //.requestMatchers(HttpMethod.GET, "/home").authenticated() // Atualizado para requestMatchers
                //.requestMatchers(HttpMethod.POST, "/auth/register").hasAnyRole("ADMIN", "MANAGER", "PATIENT") 
            ) 
            .logout(logout -> logout 
                .logoutUrl("/auth/logout") // Define a URL de logout 
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK); 
                })
                .invalidateHttpSession(true) // Invalida a sessão 
                .deleteCookies("JSESSIONID") // Exclui cookies 
            )
            .addFilterBefore((Filter) securityFilter, UsernamePasswordAuthenticationFilter.class) 
            .build(); 
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
