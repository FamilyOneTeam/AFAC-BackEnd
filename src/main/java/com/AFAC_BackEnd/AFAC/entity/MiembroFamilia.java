package com.AFAC_BackEnd.AFAC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "miembros_familia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroFamilia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "familia_id", nullable = false)
    private FichaFamilia familia;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, unique = true, length = 12)
    private String rut;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 50)
    private String relacion;

    @Column(precision = 12, scale = 2)
    private BigDecimal ingreso;
}
