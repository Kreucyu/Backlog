package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.CreateUsuarioDto;
import com.backlog_user_service.user_service.dto.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CreateUsuarioDto criarUsuario(CreateUsuarioDto createUsuarioDto) {
        Usuario usuario = new Usuario(createUsuarioDto.getNomeUsuario(),
                createUsuarioDto.getDataNascimento(),
                createUsuarioDto.getEmailUsuario(),
                createUsuarioDto.getSenhaUsuario());
        usuarioRepository.save(usuario);
        return createUsuarioDto;
    }

    public List<RecoveryUsuarioDto> listarUsuarios() {
        List<Usuario> usuariosListados = this.usuarioRepository.findAll();
        List<RecoveryUsuarioDto> usuariosRecovery = usuariosListados
                .stream()
                .map(usuario ->
                        (new RecoveryUsuarioDto(usuario.getNomeUsuario(),
                                usuario.getEmailUsuario(),
                                usuario.getDataNascimento())))
                .toList();
        return usuariosRecovery;
    }
}
