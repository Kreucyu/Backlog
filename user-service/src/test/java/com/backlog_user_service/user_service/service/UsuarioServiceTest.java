package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.entity.NiveisUsuario;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.exceptions.EmailDuplicadoException;
import com.backlog_user_service.user_service.exceptions.NomeDeUsuarioDuplicadoException;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void start() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuarioSucesso() {
        RegisterUsuarioDto registerUsuarioDto = new RegisterUsuarioDto("Marcio", "MarcioMM@gmail.com", "Marcio123@", LocalDate.now());
        usuarioService.criarUsuario(registerUsuarioDto);
        verify(usuarioRepository, times(1)).save(any());
        verify(usuarioRepository, times(1)).findByEmailUsuario("MarcioMM@gmail.com");
        verify(usuarioRepository, times(1)).existsUsuarioByNomeUsuario("Marcio");
    }

    @Test
    void criarUsuarioEmailJaUtilizado() {
        String senhaCriptografada = new BCryptPasswordEncoder().encode("Marcio123@");
        Usuario usuario = new Usuario("Marcio",
                LocalDate.now(),
                "MarcioMM@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);
        when(usuarioRepository.findByEmailUsuario("MarcioMM@gmail.com")).thenReturn(usuario);
        RegisterUsuarioDto registerUsuarioDto = new RegisterUsuarioDto("Junior", "MarcioMM@gmail.com", "Marcio123@", LocalDate.now());
        Exception thrown = Assertions.assertThrows(EmailDuplicadoException.class, () -> usuarioService.criarUsuario(registerUsuarioDto));
        Assertions.assertEquals("Email já cadastrado.", thrown.getMessage());
        verify(usuarioRepository, never()).save(any());
        verify(usuarioRepository, times(1)).findByEmailUsuario("MarcioMM@gmail.com");
        verify(usuarioRepository, never()).existsUsuarioByNomeUsuario("Marcio");
    }

    @Test
    void criarUsuarioNomeJaUtilizado() {
        when(usuarioRepository.existsUsuarioByNomeUsuario("Marcio")).thenReturn(true);
        RegisterUsuarioDto registerUsuarioDto = new RegisterUsuarioDto("Marcio", "Marcio123@gmail.com", "Marcio123@", LocalDate.now());
        Exception thrown = Assertions.assertThrows(NomeDeUsuarioDuplicadoException.class, () -> usuarioService.criarUsuario(registerUsuarioDto));
        Assertions.assertEquals("O nome de usuário já está em uso.", thrown.getMessage());
        verify(usuarioRepository, never()).save(any());
        verify(usuarioRepository, times(1)).findByEmailUsuario("Marcio123@gmail.com");
        verify(usuarioRepository, times(1)).existsUsuarioByNomeUsuario("Marcio");
    }


    @Test
    void listarUsuarios() {
    }

    @Test
    void atualizarUsuario() {
    }

    @Test
    void criarAdmin() {
    }

    @Test
    void exibirUsuario() {
    }
}