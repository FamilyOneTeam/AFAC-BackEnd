package com.AFAC_BackEnd.AFAC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ficha_infante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaInfante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombreInfante;

    @Column(nullable = false, unique = true, length = 12)
    private String rutInfante;

    @Column(nullable = false)
    private LocalDate fechaNacimientoInfante;

    private String region;
    private String comuna;
    private String direccion;

    @Column(precision = 12, scale = 2)
    private BigDecimal ingresoInfante;

    private Integer edadNna;

    @Column(length = 50)
    private String rangoEdadNna;

    @Column(length = 10)
    private String sexoNna;

    private String nacionalidadNna;

    @Column(precision = 12, scale = 2)
    private BigDecimal ingresoAfac;
}

