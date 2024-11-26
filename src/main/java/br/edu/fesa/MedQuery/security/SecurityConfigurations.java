package br.edu.fesa.MedQuery.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.core.env.Environment;

import br.edu.fesa.MedQuery.controller.handlers.CustomAuthenticationSuccessHandler;
import br.edu.fesa.MedQuery.service.GestorUserDetailsService;
import br.edu.fesa.MedQuery.service.MedicoUserDetailsService;
import br.edu.fesa.MedQuery.service.PacienteUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations{
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private PacienteUserDetailsService pacienteUserDetailsService;

    @Autowired
    private GestorUserDetailsService gestorUserDetailsService;

    @Autowired
    private MedicoUserDetailsService medicoUserDetailsService;

    @Autowired
    private Environment env;

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //Devido a versão do spring boot do projeto, é necessário utilizar o SecurityFilterChain
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions().disable());
        }

        http.cors(cors -> cors.disable()) // Configurações de CORS
        .csrf(csrf -> csrf.disable()) // Desativa CSRF
        .authorizeHttpRequests(auth -> auth
            .requestMatchers( "/css/**", "/js/**", "/img/**", "/fonts/**", "/login/**", "/cadastro/**", "/cadastro-user/**", "/vendors/**", "/cadastro-user").permitAll()
            .anyRequest().authenticated()
            //.anyRequest().permitAll()
        )
        .formLogin(form -> form //após login, o usuário é automaticamente redirecionado
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .logoutSuccessUrl("/login")
            .permitAll()
        );
        // .rememberMe(rememberMe -> rememberMe
        //     .key("keyRemember-me")
        // );

        // Desabilitar cache para evitar problemas de controle de cache em arquivos estáticos
        //http.headers(headers -> headers.cacheControl(cache -> cache.disable()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
        .userDetailsService(pacienteUserDetailsService)
        .passwordEncoder(passwordEncoder());

        authenticationManagerBuilder
        .userDetailsService(gestorUserDetailsService)
        .passwordEncoder(passwordEncoder());

        authenticationManagerBuilder
        .userDetailsService(medicoUserDetailsService)
        .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
