package com.AFAC_BackEnd.AFAC.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long usuarioId;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 500, message = "El mensaje debe tener entre 10 y 500 caracteres")
    private String mensaje;

    @NotNull(message = "La fecha de envío no puede ser nula")
    @PastOrPresent(message = "La fecha de envío no puede ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEnvio;

    @NotBlank(message = "El tipo de notificación no puede estar vacío")
    @Size(max = 50, message = "El tipo de notificación no puede exceder 50 caracteres")
    private String tipoNotificacion;
}

