package cl.somosafac.afacbackend.security;

import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.mapper.UsuarioMapper;
import cl.somosafac.afacbackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Validar correo único
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            log.warn("Intento de registro con correo existente: {}", request.getCorreo());
            throw new RuntimeException("El correo ya está registrado");
        }

        // Crear nuevo usuario
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
                .aceptarTerminos(false)
                .roles(new HashSet<>())
                .build();

        // Agregar rol principal
        usuario.getRoles().add(request.getTipoUsuario());

        try {
            // Guardar usuario
            var savedUser = usuarioRepository.save(usuario);

            // Generar token
            var token = jwtService.getToken(savedUser);
            UsuarioDTO usuarioDTO = usuarioMapper.toDTO(savedUser);

            log.info("Usuario registrado exitosamente: {}", request.getCorreo());

            return AuthResponse.builder()
                    .token(token)
                    .usuarioDTO(usuarioDTO)
                    .build();
        } catch (Exception e) {
            log.error("Error durante el registro de usuario", e);
            throw new RuntimeException("No se pudo completar el registro", e);
        }
    }

    @Transactional
    public AuthResponse authenticate(LoginRequest request) {
        try {
            // Buscar usuario
            UsuarioEntity usuario = usuarioRepository.findByCorreo(request.getCorreo())
                    .orElseThrow(() -> {
                        log.warn("Intento de login con correo no existente: {}", request.getCorreo());
                        throw new BadCredentialsException("Credenciales inválidas");
                    });

            // Verificar estado del usuario
            if (Boolean.FALSE.equals(usuario.getActivo())) {
                log.warn("Intento de login de usuario inactivo: {}", request.getCorreo());
                throw new BadCredentialsException("Usuario inactivo");
            }

            // Autenticar
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreo(),
                            request.getContrasenaHash()
                    )
            );

            // Actualizar último acceso
            usuario.setFechaUltimoAcceso(LocalDateTime.now());
            usuarioRepository.save(usuario);

            // Generar token
            var token = jwtService.getToken(usuario);
            UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

            log.info("Autenticación exitosa para usuario: {}", request.getCorreo());

            return AuthResponse.builder()
                    .token(token)
                    .usuarioDTO(usuarioDTO)
                    .build();

        } catch (BadCredentialsException e) {
            log.error("Error de autenticación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado durante la autenticación", e);
            throw new RuntimeException("Error de autenticación", e);
        }
    }

    public void validarCorreoUnico(String correo) {
        if (usuarioRepository.findByCorreo(correo).isPresent()) {
            log.warn("Intento de registro con correo existente: {}", correo);
            throw new RuntimeException("El correo ya está registrado");
        }
    }

    @Transactional
    public void cambiarContrasena(String correo, ChangePasswordRequest request) {
        // Buscar usuario
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> {
                    log.warn("Intento de cambio de contraseña con correo no existente: {}", correo);
                    throw new RuntimeException("Usuario no encontrado");
                });

        // Verificar contraseña actual
        if (!passwordEncoder.matches(request.getCurrentPassword(), usuario.getContrasenaHash())) {
            log.warn("Intento de cambio de contraseña con contraseña incorrecta para usuario: {}", correo);
            throw new BadCredentialsException("Contraseña actual incorrecta");
        }

        // Verificar que aceptó los términos
        if (Boolean.FALSE.equals(request.getAceptarTerminos())) {
            log.warn("Intento de cambio de contraseña sin aceptar términos para usuario: {}", correo);
            throw new RuntimeException("Debe aceptar los términos y condiciones");
        }

        // Actualizar contraseña y aceptar términos
        usuario.setContrasenaHash(passwordEncoder.encode(request.getNewPassword()));
        usuario.setAceptarTerminos(true);
        usuarioRepository.save(usuario);

        log.info("Contraseña actualizada exitosamente para usuario: {}", correo);
    }
}