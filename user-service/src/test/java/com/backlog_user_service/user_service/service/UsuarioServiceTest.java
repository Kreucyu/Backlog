package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.Request.RegisterUsuarioDto;
import com.backlog_user_service.user_service.dto.Request.UpdateUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.RecoveryUsuarioDto;
import com.backlog_user_service.user_service.entity.NiveisUsuario;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.exceptions.EmailDuplicadoException;
import com.backlog_user_service.user_service.exceptions.NomeDeUsuarioDuplicadoException;
import com.backlog_user_service.user_service.exceptions.UsuarioInexistenteException;
import com.backlog_user_service.user_service.exceptions.ValoresVaziosException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.IO.println;
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
        String senhaCriptografada = new BCryptPasswordEncoder().encode("Marcio123@");
        Usuario usuario1 = new Usuario("Marcio1",
                LocalDate.now(),
                "MarcioMM1@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);
        Usuario usuario2 = new Usuario("Marcio2",
                LocalDate.now(),
                "MarcioMM2@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);
        Usuario usuario3 = new Usuario("Marcio3",
                LocalDate.now(),
                "MarcioMM3@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);
        Usuario usuario4 = new Usuario("Marcio4",
                LocalDate.now(),
                "MarcioMM4@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);

        List<Usuario> usuarioList = new ArrayList<>();

        usuarioList.add(usuario1);
        usuarioList.add(usuario2);
        usuarioList.add(usuario3);
        usuarioList.add(usuario4);

        when(usuarioRepository.findAll()).thenReturn(usuarioList);

        Assertions.assertNotNull(usuarioList);

        usuarioService.listarUsuarios().forEach(System.out::println);

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void atualizarUsuarioSucesso() {
        String senhaCriptografada = new BCryptPasswordEncoder().encode("Marcio123@");

        Usuario usuario = new Usuario("Marcio1",
                LocalDate.now(),
                "MarcioMM1@gmail.com",
                senhaCriptografada,
                NiveisUsuario.USER);

        long id = 1;
        usuario.setId(id);

        System.out.println(usuario);

        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        String senhaCriptografadaUpdate = new BCryptPasswordEncoder().encode("senhateste@");
        UpdateUsuarioDto updateUsuarioDto = new UpdateUsuarioDto("Antonio", senhaCriptografadaUpdate);

        usuarioService.atualizarUsuario(id, updateUsuarioDto);

        verify(usuarioRepository, times(1)).existsById(id);
        verify(usuarioRepository, times(1)).findById(id);

        System.out.println(usuario);
    }

    @Test
    void atualizarUsuarioInexistente() {
        long id = 1;
        String senhaCriptografadaUpdate = new BCryptPasswordEncoder().encode("senhateste@");
        UpdateUsuarioDto updateUsuarioDto = new UpdateUsuarioDto("Antonio", senhaCriptografadaUpdate);
        when(usuarioRepository.existsById(id)).thenReturn(false);
        Exception thrown = Assertions.assertThrows(UsuarioInexistenteException.class, () -> usuarioService.atualizarUsuario(id, updateUsuarioDto));
        Assertions.assertEquals("Usuário não encontrado", thrown.getMessage());
        verify(usuarioRepository, times(1)).existsById(id);
    }

    @Test
    void atualizarUsuarioSemDados() {
        long id = 1;
        UpdateUsuarioDto updateUsuarioDto = new UpdateUsuarioDto(null, "");
        when(usuarioRepository.existsById(id)).thenReturn(true);
        Exception thrown = Assertions.assertThrows(ValoresVaziosException.class, () -> usuarioService.atualizarUsuario(id, updateUsuarioDto));
        Assertions.assertEquals("Nenhuma alteração foi feita.", thrown.getMessage());
        verify(usuarioRepository, times(1)).existsById(id);
    }

    @Test
    void criarAdmin() {
    }

    @Test
    void exibirUsuario() {
    }
}