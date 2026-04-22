package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.dto.Request.UpdateUsuarioDto;
import com.backlog_user_service.user_service.entity.NiveisUsuario;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.exceptions.EmailDuplicadoException;
import com.backlog_user_service.user_service.exceptions.NomeDeUsuarioDuplicadoException;
import com.backlog_user_service.user_service.exceptions.UsuarioInexistenteException;
import com.backlog_user_service.user_service.exceptions.ValoresVaziosException;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarUsuario(RegisterUsuarioDto registerDto) {
        if(this.usuarioRepository.findByEmailUsuario(registerDto.emailUsuario()) != null) throw new EmailDuplicadoException("Email já cadastrado.");
        if(this.usuarioRepository.existsUsuarioByNomeUsuario(registerDto.nomeUsuario())) throw new NomeDeUsuarioDuplicadoException("O nome de usuário já está em uso.");
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDto.senhaUsuario());
        Usuario usuario = new Usuario(registerDto.nomeUsuario(),
                registerDto.dataNascimento(),
                registerDto.emailUsuario(),
                senhaCriptografada,
                NiveisUsuario.USER);
        usuarioRepository.save(usuario);
    }

    public List<RecoveryUsuarioDto> listarUsuarios() {
        List<Usuario> usuariosListados = this.usuarioRepository.findAll();
        return usuariosListados.stream().map(this::exibirUsuarioUser).toList();
    }

    public void atualizarUsuario(long id, UpdateUsuarioDto updateUsuarioDto) {
        if(!usuarioRepository.existsById(id)) throw new UsuarioInexistenteException("Usuário não encontrado");

        if ((updateUsuarioDto.nomeUsuario() == null
                || updateUsuarioDto.nomeUsuario().equals(""))
                && (updateUsuarioDto.senhaUsuario()  == null
                || updateUsuarioDto.senhaUsuario().equals("")))
            throw new ValoresVaziosException("Nenhuma alteração foi feita.");

        Usuario usuario = usuarioRepository.findById(id).get();

        if(updateUsuarioDto.nomeUsuario() != null ) usuario.setNomeUsuario(updateUsuarioDto.nomeUsuario());
        if(updateUsuarioDto.senhaUsuario() != null) usuario.setSenhaUsuario(updateUsuarioDto.senhaUsuario());
    }

    public ResponseEntity<String> criarAdmin(RegisterUsuarioDto registerDto) {
        if(this.usuarioRepository.findByEmailUsuario(registerDto.emailUsuario()) != null) throw new EmailDuplicadoException("Email já cadastrado.");
        if(this.usuarioRepository.existsUsuarioByNomeUsuario(registerDto.nomeUsuario())) throw new NomeDeUsuarioDuplicadoException("O nome de usuário já está em uso.");
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDto.senhaUsuario());
        Usuario usuario = new Usuario(registerDto.nomeUsuario(),
                registerDto.dataNascimento(),
                registerDto.emailUsuario(),
                senhaCriptografada,
                NiveisUsuario.ADMIN);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Admin registrado com sucesso");
    }

    public RecoveryUsuarioDto exibirUsuarioUser(Usuario usuario) {
        return new RecoveryUsuarioDto(usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDataNascimento(),
                usuario.getNiveisUsuario());
    }

    public RecoveryUsuarioDto exibirUsuarioId(Long id) {
        if(!usuarioRepository.existsById(id)) throw new UsuarioInexistenteException("Usuário não encontrado");

        Usuario usuario = usuarioRepository.findById(id).get();

        return new RecoveryUsuarioDto(usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDataNascimento(),
                usuario.getNiveisUsuario());
    }
}
