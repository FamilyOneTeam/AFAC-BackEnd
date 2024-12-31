package com.AFAC_BackEnd.AFAC.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_acceso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaAcceso;

    @Column(nullable = false, length = 50)
    private String ip;

    @Column(nullable = false, length = 100)
    private String dispositivo;

    @Column(nullable = false, length = 100)
    private String navegador;

    @Column(nullable = false, length = 100)
    private String sistemaOperativo;

    @Column(nullable = false, length = 50)
    private String accion;

    @Column(columnDefinition = "TEXT")
    private String detalles;
}

