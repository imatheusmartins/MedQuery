/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.MedQuery.service;

import br.edu.fesa.MedQuery.repositories.UserRepository;
import br.edu.fesa.MedQuery.security.TokenService;
import br.edu.fesa.MedQuery.dtos.AuthenticationDto;
import br.edu.fesa.MedQuery.dtos.LoginResponseDto;
import br.edu.fesa.MedQuery.dtos.RegisterDto;
import br.edu.fesa.MedQuery.model.UserModel;
import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author hugok
 */
@Service
public class AuthorizationService implements UserDetailsService {

     @Autowired
     UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
    
}
