package cl.somosafac.afacbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "ficha_familias")
public class FichaFamiliasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    private String nombreFaUno;
    private String nombreFaDos;
    private String rutFaUno;
    private String rutFaDos;

    private LocalDateTime fechaNacimientoFaUno;
    private LocalDateTime fechaNacimientoFaDos;

    private String estadoCivil;
    private String telefono;
    private String email;
    private String region;
    private String comuna;
    private String direccion;

    private String programaFundacionActual;
    private String programaFundacionAnterior;

    private Double ingresoFa;

    private String estadoAcogimiento;
    private LocalDateTime fechaInicioAcogimiento;
}

