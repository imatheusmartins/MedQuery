package br.edu.fesa.MedQuery.model;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.fesa.MedQuery.enums.UserRole;

public class MedicoUserDetailsImpl implements UserDetails {

private Medico medico;

    public MedicoUserDetailsImpl(Medico medico) {
        this.medico = medico;
    }

    public Integer getId(){
        return medico.getId();
    }

    public String getNome(){
        return medico.getNome();
    }

    public String displayImage(){
        return medico.getImagem();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole userRole = medico.getPerfil();
        if(userRole == UserRole.ADMIN){
            userRole = UserRole.ADMIN;
        }else{
            userRole = UserRole.PACIENTE;
        }
        return AuthorityUtils.createAuthorityList(userRole.toString());
    }

    @Override
    public String getPassword() {
        return medico.getSenha();
    }

    @Override
    public String getUsername() {
         return medico.getEmail();
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
