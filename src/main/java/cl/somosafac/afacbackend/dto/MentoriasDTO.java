package com.AFAC_BackEnd.AFAC.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MentoriasDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("familiaMentoraId")
    private Long familiaMentoraId;

    @JsonProperty("familiaMentoradaId")
    private Long familiaMentoradaId;

    @JsonProperty("fechaAsignacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaAsignacion;

    @JsonProperty("estadoMentoria")
    private String estadoMentoria;
}
