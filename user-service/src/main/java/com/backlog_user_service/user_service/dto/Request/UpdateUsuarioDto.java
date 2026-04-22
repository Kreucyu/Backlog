package com.backlog_user_service.user_service.dto.Request;

import jakarta.validation.constraints.Size;
import org.jspecify.annotations.Nullable;

public record UpdateUsuarioDto(@Nullable String nomeUsuario,
                               @Nullable @Size(min = 6, max = 100)
                               String senhaUsuario) {}
