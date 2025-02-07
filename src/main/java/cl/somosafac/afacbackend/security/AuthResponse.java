package cl.somosafac.afacbackend.security;


import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("usuario")
    private UsuarioDTO usuarioDTO;
}