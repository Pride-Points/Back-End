package com.pridepoints.api.dto.Autenticacao;

import com.pridepoints.api.entities.Fisica;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PessoaFisicaDetalhesDTO implements UserDetails {
    private final String email;
    private final String senha;
    private final String nome;

    public PessoaFisicaDetalhesDTO(Fisica fisica) {
        this.email = fisica.getEmail();
        this.senha = fisica.getSenha();
        this.nome = fisica.getNome();
    }



    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
