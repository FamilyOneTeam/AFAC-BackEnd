package com.AFAC_BackEnd.AFAC.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "acogimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acogimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "familia_id", nullable = false)
    private FichaFamilia familia;

    @ManyToOne
    @JoinColumn(name = "infante_id", nullable = false)
    private FichaInfante infante;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Column(length = 50)
    private String estadoAcogimiento;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;

     private LocalDate fechaCreacion;
}

