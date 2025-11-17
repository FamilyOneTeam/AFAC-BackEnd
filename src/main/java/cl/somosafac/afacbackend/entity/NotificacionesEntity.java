package cl.somosafac.afacbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notificaciones")
public class NotificacionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    private String mensaje;
    private LocalDateTime fechaEnvio;
    private String tipoNotificacion;

    // Manual getters/setters for Lombok compatibility issue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}