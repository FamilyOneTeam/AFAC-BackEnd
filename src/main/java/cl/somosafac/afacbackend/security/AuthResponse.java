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

    // Manual builder for Lombok compatibility issue
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private UsuarioDTO usuarioDTO;

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder usuarioDTO(UsuarioDTO usuarioDTO) {
            this.usuarioDTO = usuarioDTO;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token, usuarioDTO);
        }
    }
}
