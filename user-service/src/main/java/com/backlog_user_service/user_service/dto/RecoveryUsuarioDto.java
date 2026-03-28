package com.backlog_user_service.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecoveryUsuarioDto {
    private String nomeUsuario;
    @Email(message = "O Email não é válido.")
    @NotEmpty(message = "O Email não pode ser vazio.")
    private String emailUsuario;
    private LocalDate dataNascimento;
}

