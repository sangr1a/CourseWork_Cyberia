package com.example.cyberia.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_BUYER, ROLE_ADMIN, ROLE_SELLER;

    @Override
    public String getAuthority() {
        return name();
    }
}
