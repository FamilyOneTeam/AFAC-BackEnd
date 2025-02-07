package cl.somosafac.afacbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotasDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("familiaId")
    @NotNull(message = "El ID de la familia no puede ser nulo")
    private Long familiaId;


    @JsonProperty("descripcion")
    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 5, max = 500, message = "La descripción debe tener entre 5 y 500 caracteres")
    private String descripcion;

    @JsonProperty("fechaCreacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;
}