package com.backlog_user_service.user_service.exceptions;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
