package com.AFAC_BackEnd.AFAC.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fichas_familias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaFamilia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    private String estadoCivil;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "El teléfono debe tener un formato válido")
    private String telefono;

    private String email;

    private String region;


    private String comuna;

    private String direccion;

    private String programaFundacionActual;

    private String programaFundacionAnterior;

    @DecimalMin(value = "0.0", inclusive = true, message = "El salario debe ser un valor positivo")
    private BigDecimal salarioTotal;

    @Min(value = 0, message = "La cantidad de acogimientos no puede ser negativa")
    private Integer cantidadAcogimientos = 0;

    @Size(max = 10, message = "El estado de acogimiento no puede exceder los 10 caracteres")
    private String estadoAcogimiento;

    @PastOrPresent(message = "La fecha del último contacto no puede ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaUltimoContacto;

    private String usuarioCreacion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;
}
