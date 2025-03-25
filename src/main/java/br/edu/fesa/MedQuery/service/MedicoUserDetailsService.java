package br.edu.fesa.MedQuery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.model.MedicoUserDetailsImpl;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;

@Service
public class MedicoUserDetailsService implements UserDetailsService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medico medico = medicoRepository.findByEmail(username)
        .orElseThrow( () -> new UsernameNotFoundException("Usuário não foi encontrado na base de dados"));
        return new MedicoUserDetailsImpl(medico);
    }
}
