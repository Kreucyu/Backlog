package com.backlog_user_service.user_service.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthenticationUsuarioDto(
        @Email(message = "O Email não é válido.")
        @NotEmpty(message = "O Email não pode ser vazio.")
        String emailUsuario,
        @Size(min = 6, max = 100)
        String senhaUsuario) {
}
