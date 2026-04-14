package com.backlog_user_service.user_service.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@NotNull
public enum NiveisUsuario {
    USER("user"),
    ADMIN("admin");

    private String nivel;

    NiveisUsuario(String nivel) {
        this.nivel = nivel;
    }

}
