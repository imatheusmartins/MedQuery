// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package br.edu.fesa.MedQuery.controller;

// import br.edu.fesa.MedQuery.dtos.AuthenticationDto;
// import br.edu.fesa.MedQuery.dtos.LoginResponseDto;
// import br.edu.fesa.MedQuery.dtos.RegisterDto;
// import br.edu.fesa.MedQuery.model.UserModel;
// import br.edu.fesa.MedQuery.repositories.UserRepository;
// // import br.edu.fesa.MedQuery.security.TokenService;
// import jakarta.validation.Valid;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// /**
//  *
//  * @author hugok
//  */
// @RestController
// @RequestMapping("/auth")
// public class AuthenticationController {
//     @Autowired
//     private AuthenticationManager authenticationManager;
//     @Autowired
//     private UserRepository repository;
//     @Autowired
//     private TokenService tokenService;

//     @PostMapping("/login")
//     public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
//         var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
//         var auth = this.authenticationManager.authenticate(usernamePassword);

//         var token = tokenService.generateToken((UserModel) auth.getPrincipal());

//         return ResponseEntity.ok(new LoginResponseDto(token));
//     }

//     @PostMapping("/register")
//     public ResponseEntity register(@RequestBody @Valid RegisterDto data){
//         if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

//         String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//         UserModel newUser = new UserModel(data.email(), encryptedPassword, data.userRole());

//         this.repository.save(newUser);

//         return ResponseEntity.ok().build();
//     }
// }
