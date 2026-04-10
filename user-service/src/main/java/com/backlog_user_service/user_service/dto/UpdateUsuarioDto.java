package com.backlog_user_service.user_service.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUsuarioDto {
    private String nomeUsuario;
    @Size(min = 6, max = 100)
    private String senhaUsuario;
}
