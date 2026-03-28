package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.CreateUsuarioDto;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
