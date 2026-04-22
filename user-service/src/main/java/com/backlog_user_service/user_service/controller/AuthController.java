package com.backlog_user_service.user_service.controller;

import com.backlog_user_service.user_service.dto.Request.AuthenticationUsuarioDto;
import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.LoginResponseDto;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import com.backlog_user_service.user_service.service.AuthService;
import com.backlog_user_service.user_service.service.TokenService;
import com.backlog_user_service.user_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationUsuarioDto authenticationDto) {
        return authService.logarUsuario(authenticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterUsuarioDto registerDto) {
        return usuarioService.criarUsuario(registerDto);
    }
}
