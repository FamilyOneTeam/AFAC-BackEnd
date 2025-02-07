package cl.somosafac.afacbackend.security;

import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.mapper.UsuarioMapper;
import cl.somosafac.afacbackend.repository.UsuarioRepository;
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
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .contrasenaHash(passwordEncoder.encode(request.getContrasenaHash()))
                .cargo(request.getCargo())
                .tipoUsuario(request.getTipoUsuario())
                .activo(true)
                .verificado(false)
                .fechaRegistro(LocalDateTime.now())
                .aceptarTerminos(request.isAceptarTerminos())
                .roles(new HashSet<>())
                .build();

        // Agregar el rol principal basado en el tipo de usuario
        usuario.getRoles().add(request.getTipoUsuario());

        var savedUser = usuarioRepository.save(usuario);
        var token = jwtService.getToken(savedUser);
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(savedUser);

        return AuthResponse.builder()
                .token(token)
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
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setFechaUltimoAcceso(LocalDateTime.now());
        usuarioRepository.save(usuario);

        var token = jwtService.getToken(usuario);
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

        return AuthResponse.builder()
                .token(token)
                .usuarioDTO(usuarioDTO)
                .build();
    }

    public void validarCorreoUnico(String correo) {
        if (usuarioRepository.findByCorreo(correo).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
    }
}