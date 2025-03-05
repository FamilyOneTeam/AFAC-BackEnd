package cl.somosafac.afacbackend.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOTest {

    private Validator validator;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        usuarioDTO = new UsuarioDTO();
    }

    @Test
    void cuandoTodosLosCamposValidosNoDeberiaHaberViolaciones() {
        // Arrange
        usuarioDTO.setNombre("Juan");
        usuarioDTO.setApellido("Pérez");
        usuarioDTO.setCorreo("juan.perez@example.com");
        usuarioDTO.setCargo("Administrador");
        usuarioDTO.setTipoUsuario("ADMIN");
        usuarioDTO.setActivo(true);
        usuarioDTO.setVerificado(true);
        usuarioDTO.setFechaRegistro(LocalDateTime.now());
        usuarioDTO.setAceptarTerminos(true);
        usuarioDTO.setRoles(Set.of("ADMIN"));

        // Act
        var violations = validator.validate(usuarioDTO);

        // Assert
        assertTrue(violations.isEmpty(), "No deberían existir violaciones de validación");
    }

    @Test
    void cuandoNombreInvalidoDeberiaHaberViolaciones() {
        // Arrange
        usuarioDTO.setNombre("Juan123"); // Nombre con números

        // Act
        var violations = validator.validate(usuarioDTO);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("nombre"))
                .count());
    }

    @Test
    void cuandoCorreoInvalidoDeberiaHaberViolaciones() {
        // Arrange
        usuarioDTO.setCorreo("correo.invalido");

        // Act
        var violations = validator.validate(usuarioDTO);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("correo"))
                .count());
    }

    @Test
    void cuandoTipoUsuarioInvalidoDeberiaHaberViolaciones() {
        // Arrange
        usuarioDTO.setTipoUsuario("INVALID_TYPE");

        // Act
        var violations = validator.validate(usuarioDTO);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("tipoUsuario"))
                .count());
    }

    @Test
    void cuandoRolesVaciosDeberiaHaberViolaciones() {
        // Arrange
        usuarioDTO.setRoles(Set.of());

        // Act
        var violations = validator.validate(usuarioDTO);

        // Assert
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("roles"))
                .count());
    }
} 