package com.backlog_user_service.user_service.dto;

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
