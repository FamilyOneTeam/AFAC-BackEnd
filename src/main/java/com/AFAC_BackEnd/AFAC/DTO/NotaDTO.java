package com.AFAC_BackEnd.AFAC.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotaDTO {


    private Long id;


    private Long familiaId;


    private Long voluntarioId;


    private String descripcion;


    private LocalDateTime fechaCreacion;
}

