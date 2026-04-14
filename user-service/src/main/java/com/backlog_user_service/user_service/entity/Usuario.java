package com.backlog_user_service.user_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NotNull
@ToString
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Email(message = "O Email não é válido.")
    @NotEmpty(message = "O Email não pode ser vazio.")
    @Column(nullable = false)
    private String emailUsuario;

    @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String senhaUsuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NiveisUsuario niveisUsuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<JogoUsuario> jogosFavoritosUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<JogoUsuario> jogosWishlistUsuario;

    public Usuario(String nomeUsuario, LocalDate dataNascimento, String emailUsuario, String senhaUsuario, NiveisUsuario niveisUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.dataNascimento = dataNascimento;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.niveisUsuario = niveisUsuario;
        this.jogosFavoritosUsuario = new ArrayList<>();
        this.jogosWishlistUsuario = new ArrayList<>();
    }

    public void adicionarJogoAosFavoritos(JogoUsuario jogoUsuario) {
        this.jogosFavoritosUsuario.add(jogoUsuario);
    }

    public void adicionarJogoNoBacklog(JogoUsuario jogoUsuario) {
        this.jogosWishlistUsuario.add(jogoUsuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.niveisUsuario == NiveisUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senhaUsuario;
    }

    @Override
    public String getUsername() {
        return this.emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
