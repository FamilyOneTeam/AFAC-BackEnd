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
@Table(name = "mentorias")
public class MentoriasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "familia_mentora_id", nullable = false)
    private FichaFamiliasEntity familiaMentora;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "familia_mentorada_id", nullable = false)
    private FichaFamiliasEntity familiaMentorada;

    private LocalDateTime fechaAsignacion;
    private String estadoMentoria;

    // Manual getters/setters for Lombok compatibility issue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}