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
@Table(name = "acogimientos")
public class AcogimientosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "familia_id", nullable = false)
    private FichaFamiliasEntity familia;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "infante_id", nullable = false)
    private FichaInfanteEntity infante;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String estadoAcogimiento;
}