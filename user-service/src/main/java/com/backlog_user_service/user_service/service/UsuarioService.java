package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.dto.Request.UpdateUsuarioDto;
import com.backlog_user_service.user_service.entity.NiveisUsuario;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<String> criarUsuario(RegisterUsuarioDto registerDto) {
        if(this.usuarioRepository.findByEmailUsuario(registerDto.emailUsuario()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        if(this.usuarioRepository.existsUsuarioByNomeUsuario(registerDto.nomeUsuario())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Nome de usuário já está em uso");
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDto.senhaUsuario());
        Usuario usuario = new Usuario(registerDto.nomeUsuario(),
                registerDto.dataNascimento(),
                registerDto.emailUsuario(),
                senhaCriptografada,
                NiveisUsuario.USER);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    public List<RecoveryUsuarioDto> listarUsuarios() {
        List<Usuario> usuariosListados = this.usuarioRepository.findAll();
        List<RecoveryUsuarioDto> usuariosRecovery = usuariosListados
                .stream()
                .map(usuario ->
                        (new RecoveryUsuarioDto(usuario.getNomeUsuario(),
                                usuario.getEmailUsuario(),
                                usuario.getDataNascimento(),
                                usuario.getNiveisUsuario())))
                .toList();
        return usuariosRecovery;
    }

    public void atualizarUsuario(long id, UpdateUsuarioDto updateUsuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).get();
        try {
            if(!(updateUsuarioDto.nomeUsuario() == null || updateUsuarioDto.nomeUsuario().isEmpty()))
                usuario.setNomeUsuario(updateUsuarioDto.nomeUsuario());
            if(updateUsuarioDto.senhaUsuario() != null || !updateUsuarioDto.senhaUsuario().isEmpty()) {
                usuario.setSenhaUsuario(updateUsuarioDto.senhaUsuario());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("O valor não pode ser nulo.");
        }
        System.out.println(usuario);
    }

    public RegisterUsuarioDto criarAdmin(RegisterUsuarioDto registerDto) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDto.senhaUsuario());
        Usuario usuario = new Usuario(registerDto.nomeUsuario(),
                registerDto.dataNascimento(),
                registerDto.emailUsuario(),
                senhaCriptografada,
                NiveisUsuario.ADMIN);
        usuarioRepository.save(usuario);
        return registerDto;
    }

    public RecoveryUsuarioDto exibirUsuario(Usuario usuario) {
        RecoveryUsuarioDto recovery = new RecoveryUsuarioDto(usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDataNascimento(),
                usuario.getNiveisUsuario());
        return recovery;
    }
}
