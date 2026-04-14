package com.backlog_user_service.user_service.dto.Response;

import com.backlog_user_service.user_service.entity.NiveisUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

public record RecoveryUsuarioDto(String nomeUsuario,
                                 @Email(message = "O Email não é válido.")
                                 @NotEmpty(message = "O Email não pode ser vazio.")
                                 String emailUsuario,
                                 LocalDate dataNascimento,
                                 NiveisUsuario niveisUsuario) {
}

