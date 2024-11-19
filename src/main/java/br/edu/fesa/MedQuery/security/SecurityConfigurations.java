package br.edu.fesa.MedQuery.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.env.Environment;
import br.edu.fesa.MedQuery.service.PacienteUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations{

    @Autowired
    private PacienteUserDetailsService pacienteUserDetailsService;

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
            .requestMatchers( "/css/**", "/js/**", "/img/**").permitAll()
            .anyRequest().permitAll()
        )
        .formLogin(form -> form //após login, o usuário é automaticamente redirecionado
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
        )
        .rememberMe(rememberMe -> rememberMe
            .key("keyRemember-me")
        );

        // Desabilitar cache para evitar problemas de controle de cache em arquivos estáticos
        http.headers(headers -> headers.cacheControl(cache -> cache.disable()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
        .userDetailsService(pacienteUserDetailsService)
        .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
