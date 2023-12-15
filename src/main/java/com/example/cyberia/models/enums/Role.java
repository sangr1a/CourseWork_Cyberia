package com.example.cyberia.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_VIEWER, ROLE_ADMIN, ROLE_ORG, ROLE_PLAYER;

    @Override
    public String getAuthority() {
        return name();
    }
}
