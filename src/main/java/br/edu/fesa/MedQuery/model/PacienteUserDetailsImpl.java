package br.edu.fesa.MedQuery.model;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.fesa.MedQuery.enums.UserRole;

public class PacienteUserDetailsImpl implements UserDetails {

private Paciente paciente;

    public PacienteUserDetailsImpl(Paciente paciente) {
        this.paciente = paciente;
    }

    public UUID getId(){
        return paciente.getId();
    }

    public String getNome(){
        return paciente.getNome();
    }

    public String displayImage(){
        return paciente.getImagem();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole perfil = paciente.getPerfil();
        if(perfil == UserRole.ADMIN){
            perfil = UserRole.ADMIN;
        }else{
            perfil = UserRole.PACIENTE;
        }
        return AuthorityUtils.createAuthorityList(perfil.toString());
    }

    @Override
    public String getPassword() {
        return paciente.getSenha();
    }

    @Override
    public String getUsername() {
         return paciente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
