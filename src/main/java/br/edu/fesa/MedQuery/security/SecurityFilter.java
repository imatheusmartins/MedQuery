// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package br.edu.fesa.MedQuery.security;

// import br.edu.fesa.MedQuery.repositories.UserRepository;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// /**
//  *
//  * @author hugok
//  */
// @Component
// public class SecurityFilter extends OncePerRequestFilter {
//     @Autowired 
//     br.edu.fesa.MedQuery.security.TokenService tokenService;

//     @Autowired
//     UserRepository userRepository;
    
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         var token = this.recoverToken(request);
    
//         if (token != null) {
//             var email = tokenService.validateToken(token);
//             UserDetails user = userRepository.findByEmail(email);
    
//             if (user != null) {
//                 var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
    
//     private String recoverToken(HttpServletRequest request){
//         var authHeader = request.getHeader("Authorization");
//         if (authHeader == null) return null;
//         return authHeader.replace("Bearer ", "");
//     }
// }
