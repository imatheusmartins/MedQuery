package br.edu.fesa.MedQuery.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.fesa.MedQuery.enums.UserRole;

public class GestorUserDetailsImpl implements UserDetails{

    private Gestor gestor;

    public GestorUserDetailsImpl(Gestor gestor) {
        this.gestor = gestor;
    }

    public Integer getId(){
        return gestor.getId();
    }

    public String getNome(){
        return gestor.getNome();
    }

    public String displayImage(){
        return gestor.getImagem();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole userRole = gestor.getUserRole();
        if(userRole == UserRole.ADMIN){
            userRole = UserRole.ADMIN;
        }else{
            userRole = UserRole.GESTOR;
        }
        return AuthorityUtils.createAuthorityList(userRole.toString());
    }

    @Override
    public String getPassword() {
        return gestor.getSenha();
    }

    @Override
    public String getUsername() {
         return gestor.getEmail();
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
