package com.AFAC_BackEnd.AFAC.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "familias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String estadoCivil;
    private String telefono;
    private String email;

    private String region;
    private String comuna;
    private String direccion;

    private String programaFundacionActual;
    private String programaFundacionAnterior;

    @Column(precision = 12, scale = 2)
    private BigDecimal salarioTotal;

    private Integer cantidadAcogimientos = 0;

    @Column(length = 10)
    private String estadoAcogimiento;

    private LocalDate fechaUltimoContacto;

    private String usuarioCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaModificacion;
}

