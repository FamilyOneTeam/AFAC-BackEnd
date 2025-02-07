package cl.somosafac.afacbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AcogimientosDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("familiaId")
    private Long familiaId;

    @JsonProperty("infanteId")
    private Long infanteId;

    @JsonProperty("fechaInicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;

    @JsonProperty("fechaFin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFin;

    @JsonProperty("estadoAcogimiento")
    private String estadoAcogimiento;
}