// package br.edu.fesa.MedQuery.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import br.edu.fesa.MedQuery.model.Gestor;
// import br.edu.fesa.MedQuery.model.GestorUserDetailsImpl;
// import br.edu.fesa.MedQuery.model.PacienteUserDetailsImpl;
// import br.edu.fesa.MedQuery.repositories.GestorRepository;
// import br.edu.fesa.MedQuery.repositories.PacienteRepository;

// @Service
// public class GestorUserDetailService implements UserDetailsService {

//     @Autowired
//     private GestorRepository gestorRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Gestor gestor = gestorRepository.findByEmail(username)
//         .orElseThrow( () -> new UsernameNotFoundException("Usuário não foi encontrado na base de dados"));
//         return new GestorUserDetailsImpl(gestor);
//     }
// }
