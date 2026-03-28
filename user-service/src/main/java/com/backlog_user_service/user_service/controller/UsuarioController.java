package com.backlog_user_service.user_service.controller;

import com.backlog_user_service.user_service.dto.CreateUsuarioDto;
import com.backlog_user_service.user_service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    private ResponseEntity<CreateUsuarioDto> criarUsuario(@RequestBody CreateUsuarioDto createUsuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(createUsuarioDto));
    }

}
