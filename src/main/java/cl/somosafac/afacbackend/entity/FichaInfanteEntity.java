package com.AFAC_BackEnd.AFAC.entity;

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
@Table(name = "ficha_infante")
public class FichaInfanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreInfante;
    private String rutInfante;
    private LocalDateTime fechaNacimientoInfante;
    private String region;
    private String comuna;
    private String direccion;
    private Double ingresoInfante;
    private Integer edadNna;
    private String rangoEdadNna;
    private String sexoNna;
    private String nacionalidadNna;
    private Double ingresoAfac;
}