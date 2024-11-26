package br.edu.fesa.MedQuery.controller.handlers;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.edu.fesa.MedQuery.model.User;
import br.edu.fesa.MedQuery.repositories.UserRepository;
import br.edu.fesa.MedQuery.util.UserContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {

    @Autowired
    private UserRepository userRepository;

     @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
                                            
        String username = authentication.getName();
        User user = userRepository.findByNome(username);
        UserContext.setCurrentUser(user);
        // response.sendRedirect("/home");
    }
}
