package com.AFAC_BackEnd.AFAC.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogAccesoDTO {

    private Long id;

    private Long usuarioId;

    private LocalDateTime fechaAcceso;

    private String ip;

    private String dispositivo;

    private String navegador;

    private String sistemaOperativo;

    private String accion;

   }

