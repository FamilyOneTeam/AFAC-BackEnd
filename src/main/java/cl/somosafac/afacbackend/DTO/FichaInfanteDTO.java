package cl.somosafac.afacbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FichaInfanteDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombreInfante")
    private String nombreInfante;

    @JsonProperty("rutInfante")
    private String rutInfante;

    @JsonProperty("fechaNacimientoInfante")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaNacimientoInfante;

    @JsonProperty("region")
    private String region;

    @JsonProperty("comuna")
    private String comuna;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("ingresoInfante")
    private Double ingresoInfante;

    @JsonProperty("edadNna")
    private Integer edadNna;

    @JsonProperty("rangoEdadNna")
    private String rangoEdadNna;

    @JsonProperty("sexoNna")
    private String sexoNna;

    @JsonProperty("nacionalidadNna")
    private String nacionalidadNna;

    @JsonProperty("ingresoAfac")
    private Double ingresoAfac;
}