package cl.somosafac.afacbackend.entity;

import cl.somosafac.afacbackend.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasenaHash;

    private String cargo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role tipoUsuario;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private Boolean verificado = false;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaUltimoAcceso;

    @Column(nullable = false)
    private Boolean aceptarTerminos = false;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "verification_token_expiry")
    private LocalDateTime verificationTokenExpiry;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (roles == null) {
            roles = new HashSet<>();
        }
        if (tipoUsuario != null) {
            roles.add(tipoUsuario);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            authorities.addAll(roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toSet()));
        }
        // Agregar el tipo de usuario como autoridad
        if (tipoUsuario != null) {
            authorities.add(new SimpleGrantedAuthority(tipoUsuario.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasenaHash;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return activo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo && verificado;
    }

    // Métodos helper
    public boolean isVerified() {
        return verificado;
    }

    public boolean isActive() {
        return activo;
    }

    // Manual getters/setters for Lombok compatibility issue
    public String getCorreo() {
        return correo;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setFechaUltimoAcceso(LocalDateTime fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    public void setAceptarTerminos(Boolean aceptarTerminos) {
        this.aceptarTerminos = aceptarTerminos;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
