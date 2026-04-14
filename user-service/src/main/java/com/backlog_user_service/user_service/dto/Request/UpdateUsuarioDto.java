package com.backlog_user_service.user_service.dto.Request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateUsuarioDto(String nomeUsuario,
                               @Size(min = 6, max = 100)
                               String senhaUsuario,
                               LocalDate dataNascimento) {}
