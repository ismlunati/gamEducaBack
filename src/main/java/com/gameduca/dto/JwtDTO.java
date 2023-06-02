package com.gameduca.dto;

import org.springframework.security.core.GrantedAuthority;

import com.gameduca.entity.Usuario;

import java.util.Collection;

public class JwtDTO {
    private String token;
    private String bearer = "Bearer";
    private Usuario usuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.usuario = usuario;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
