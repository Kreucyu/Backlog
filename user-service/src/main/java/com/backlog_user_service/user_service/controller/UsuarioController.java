package com.backlog_user_service.user_service.controller;

import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.dto.Request.UpdateUsuarioDto;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import com.backlog_user_service.user_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<RecoveryUsuarioDto>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @PatchMapping("/{id}")
    private ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody UpdateUsuarioDto updateUsuarioDto) {
        return usuarioService.atualizarUsuario(id, updateUsuarioDto);
    }

    @PostMapping("/novoAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid RegisterUsuarioDto registerDto) {
        return usuarioService.criarAdmin(registerDto);
    }

    @GetMapping("/me")
    public ResponseEntity<RecoveryUsuarioDto> me() {
        var usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.exibirUsuario((Usuario) usuario));
    }

}
