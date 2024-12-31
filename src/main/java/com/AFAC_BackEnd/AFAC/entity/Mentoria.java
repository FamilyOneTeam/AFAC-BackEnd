package com.AFAC_BackEnd.AFAC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "familia_mentora_id", nullable = false)
    private FichaFamilia familiaMentora;

    @ManyToOne
    @JoinColumn(name = "familia_mentorada_id", nullable = false)
    private FichaFamilia familiaMentorada;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaAsignacion;

    @Column(length = 50)
    private String estadoMentoria;
}

