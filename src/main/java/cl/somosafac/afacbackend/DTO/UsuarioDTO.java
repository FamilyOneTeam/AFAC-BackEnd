package cl.somosafac.afacbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UsuarioDTO {
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    private String nombre;

    private String apellido;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String correo;

    private String cargo;

    @NotNull(message = "El tipo de usuario es obligatorio")
    private String tipoUsuario;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

    private Boolean verificado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaUltimoAcceso;

    private Boolean aceptarTerminos;

    @NotNull(message = "Los roles son obligatorios")
    private Set<String> roles;
}