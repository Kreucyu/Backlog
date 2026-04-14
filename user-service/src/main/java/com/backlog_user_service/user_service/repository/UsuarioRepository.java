package com.backlog_user_service.user_service.repository;

import com.backlog_user_service.user_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmailUsuario(String emailUsuario);

    boolean existsUsuarioByNomeUsuario(String nomeUsuario);
}
