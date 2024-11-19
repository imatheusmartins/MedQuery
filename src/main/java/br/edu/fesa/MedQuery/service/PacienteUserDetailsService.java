package br.edu.fesa.MedQuery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.model.PacienteUserDetailsImpl;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;

@Service
public class PacienteUserDetailsService implements UserDetailsService{
    @Autowired
    private PacienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Paciente paciente = clienteRepository.findByEmail(username)
        .orElseThrow( () -> new UsernameNotFoundException("Usuário não foi encontrado na base de dados"));
        return new PacienteUserDetailsImpl(paciente);
    }
}
