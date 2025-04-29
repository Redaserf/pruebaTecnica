package com.example.pruebatecnica.models;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
    USUARIO("USUARIO"),
    ADMIN("ADMIN");

    private String value;

    Rol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
