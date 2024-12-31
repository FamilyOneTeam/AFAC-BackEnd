package com.AFAC_BackEnd.AFAC.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un correo válido")
    private String correo;

    @NotBlank(message = "El tipo de usuario no puede estar vacío")
    @Size(max = 50, message = "El tipo de usuario no puede exceder 50 caracteres")
    private String tipoUsuario;

    @NotNull(message = "El estado activo no puede ser nulo")
    private Boolean activo;

    @NotNull(message = "El estado verificado no puede ser nulo")
    private Boolean verificado;

    @NotNull(message = "Debe aceptar los términos y condiciones")
    private Boolean aceptarTerminos;
}
