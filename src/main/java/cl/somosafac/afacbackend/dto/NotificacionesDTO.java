package com.AFAC_BackEnd.AFAC.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionesDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuarioId")
    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long usuarioId;

    @JsonProperty("mensaje")
    @NotNull(message = "El mensaje no puede ser nulo")
    @Size(min = 5, max = 500, message = "El mensaje debe tener entre 5 y 500 caracteres")
    private String mensaje;

    @JsonProperty("fechaEnvio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaEnvio;

    //TODO RECORDATORIO: AVERIGUAR SI HABRA TIPOS DE NOTIFICACIONES
    @JsonProperty("tipoNotificacion")
    @NotNull(message = "El tipo de notificación no puede ser nulo")
    private String tipoNotificacion;
}
