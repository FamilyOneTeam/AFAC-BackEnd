package cl.somosafac.afacbackend.config;

import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.repository.UsuarioRepository;
import cl.somosafac.afacbackend.security.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByCorreo("admin@somosafac.cl").isEmpty()) {
            log.info("Creando usuario administrador por defecto");

            Set<Role> roles = new HashSet<>();
            roles.add(Role.ADMIN);

            UsuarioEntity admin = UsuarioEntity.builder()
                    .nombre("Admin")
                    .apellido("Sistema")
                    .correo("admin@somosafac.cl")
                    .contrasenaHash(passwordEncoder.encode("Admin123!"))
                    .cargo("Administrador")
                    .tipoUsuario(Role.ADMIN)
                    .roles(roles)
                    .activo(true)
                    .verificado(true)
                    .fechaRegistro(LocalDateTime.now())
                    .aceptarTerminos(true)
                    .build();

            usuarioRepository.save(admin);
            log.info("Usuario administrador creado exitosamente");
        }
    }
}