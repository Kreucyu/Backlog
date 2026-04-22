package com.backlog_user_service.user_service.service;

import com.backlog_user_service.user_service.dto.Request.AuthenticationUsuarioDto;
import com.backlog_user_service.user_service.dto.Response.LoginResponseDto;
import com.backlog_user_service.user_service.entity.Usuario;
import com.backlog_user_service.user_service.repository.UsuarioRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String emailUsuario) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

    public ResponseEntity<LoginResponseDto> logarUsuario(AuthenticationUsuarioDto authenticationUsuarioDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationUsuarioDto.emailUsuario(), authenticationUsuarioDto.senhaUsuario());
        var autenticacao = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) Objects.requireNonNull(autenticacao.getPrincipal()));
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
