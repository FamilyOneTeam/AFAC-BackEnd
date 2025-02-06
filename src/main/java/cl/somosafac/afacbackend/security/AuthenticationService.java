package cl.somosafac.afacbackend.security;

import com.AFAC_BackEnd.AFAC.DTO.UsuarioDTO;
import com.AFAC_BackEnd.AFAC.entity.UsuarioEntity;
import com.AFAC_BackEnd.AFAC.mapper.UsuarioMapper;
import com.AFAC_BackEnd.AFAC.repository.UsuarioRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var usuario = UsuarioEntity.builder()
                .correo(request.getCorreo())
                .contrasenaHash(passwordEncoder.encode(request.getContrasenaHash()))
                .tipoUsuario(request.getTipoUsuario())
                .activo(request.isActivo())
                .verificado(request.isVerificado())
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimoAcceso(request.getFechaUltimoAcceso())
                .aceptarTerminos(request.isAceptarTerminos())
                .roles(new HashSet<>())
                .build();

        usuario.getRoles().add(request.getTipoUsuario());
        var savedUser = usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(savedUser);

        return AuthResponse.builder()
                .usuarioDTO(usuarioDTO)
                .build();
    }

    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasenaHash()
                )
        );

        var usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow();

        usuario.setFechaUltimoAcceso(LocalDateTime.now());
        usuarioRepository.save(usuario);

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);
        return AuthResponse.builder()
                .usuarioDTO(usuarioDTO)
                .build();
    }
}