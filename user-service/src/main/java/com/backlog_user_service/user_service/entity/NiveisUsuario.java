package com.backlog_user_service.user_service.entity;

import lombok.Getter;

@Getter
public enum NiveisUsuario {
    USER("user"),
    ADMIN("admin");

    private String nivel;

    NiveisUsuario(String nivel) {
        this.nivel = nivel;
    }

}
