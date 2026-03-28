package com.backlog_user_service.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUsuarioDto {
    private String nomeUsuario;
    @Email(message = "O Email não é válido.", regexp = "/^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+{2,}$/")
    @NotEmpty(message = "O Email não pode ser vazio.")
    private String emailUsuario;
    @Size(min = 6, max = 100)
    private String senhaUsuario;
    private LocalDate dataNascimento;
}
