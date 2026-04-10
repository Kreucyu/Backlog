package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.CreateUsuarioDto;
import com.backlog_user_service.user_service.dto.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.dto.UpdateUsuarioDto;
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

    public void atualizarUsuario(long id, UpdateUsuarioDto updateUsuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).get();
        if(updateUsuarioDto.getNomeUsuario() != null || !updateUsuarioDto.getNomeUsuario().equals("")) {
            usuario.setNomeUsuario(updateUsuarioDto.getNomeUsuario());
        }
        if(updateUsuarioDto.getSenhaUsuario() != null || !updateUsuarioDto.getSenhaUsuario().equals("")) {
            usuario.setSenhaUsuario(updateUsuarioDto.getSenhaUsuario());
        }
    }
}
