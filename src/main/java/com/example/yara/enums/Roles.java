package com.example.yara.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
