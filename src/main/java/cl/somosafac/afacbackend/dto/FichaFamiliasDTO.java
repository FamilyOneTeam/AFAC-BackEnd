package cl.somosafac.afacbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FichaFamiliasDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuarioId")
    private Long usuarioId;

    @JsonProperty("nombreFaUno")
    private String nombreFaUno;

    @JsonProperty("nombreFaDos")
    private String nombreFaDos;

    @JsonProperty("rutFaUno")
    private String rutFaUno;

    @JsonProperty("rutFaDos")
    private String rutFaDos;

    @JsonProperty("fechaNacimientoFaUno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaNacimientoFaUno;

    @JsonProperty("fechaNacimientoFaDos")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaNacimientoFaDos;

    @JsonProperty("estadoCivil")
    private String estadoCivil;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("email")
    private String email;

    @JsonProperty("region")
    private String region;

    @JsonProperty("comuna")
    private String comuna;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("programaFundacionActual")
    private String programaFundacionActual;

    @JsonProperty("programaFundacionAnterior")
    private String programaFundacionAnterior;

    @JsonProperty("ingresoFa")
    private Double ingresoFa;

    @JsonProperty("estadoAcogimiento")
    private String estadoAcogimiento;

    @JsonProperty("fechaInicioAcogimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicioAcogimiento;
}