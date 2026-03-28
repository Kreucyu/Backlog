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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NotNull
@ToString
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Email(message = "O Email não é válido.", regexp = "/^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+{2,}$/")
    @NotEmpty(message = "O Email não pode ser vazio.")
    @Column(nullable = false)
    private String emailUsuario;

    @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String senhaUsuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<JogoUsuario> jogosFavoritosUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<JogoUsuario> jogosBacklogUsuario;

    public Usuario(String nomeUsuario, LocalDate dataNascimento, String emailUsuario, String senhaUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.dataNascimento = dataNascimento;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.jogosFavoritosUsuario = new ArrayList<>();
        this.jogosBacklogUsuario = new ArrayList<>();
    }

    public void adicionarJogoAosFavoritos(JogoUsuario jogoUsuario) {
        this.jogosFavoritosUsuario.add(jogoUsuario);
    }

    public void adicionarJogoNoBacklog(JogoUsuario jogoUsuario) {
        this.jogosBacklogUsuario.add(jogoUsuario);
    }
}
