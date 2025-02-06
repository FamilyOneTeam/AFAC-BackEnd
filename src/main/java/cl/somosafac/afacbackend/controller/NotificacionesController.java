package cl.somosafac.afacbackend.controller;

import com.AFAC_BackEnd.AFAC.DTO.NotificacionesDTO;
import com.AFAC_BackEnd.AFAC.service.NotificacionesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@Validated
@Tag(name = "Notificaciones", description = "Operaciones para la gestión de notificaciones")
public class NotificacionesController {

    private final NotificacionesService service;

    public NotificacionesController(NotificacionesService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todas las notificaciones")
    @ApiResponse(responseCode = "200", description = "Notificaciones obtenidas exitosamente")
    @GetMapping
    public ResponseEntity<List<NotificacionesDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodasLasNotificaciones());
    }

    @Operation(summary = "Obtener una notificación por ID")
    @ApiResponse(responseCode = "200", description = "Notificación obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = NotificacionesDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionesDTO> obtenerPorId(@PathVariable Long id) {
        NotificacionesDTO dto = service.obtenerNotificacionPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva notificación")
    @ApiResponse(responseCode = "201", description = "Notificación creada exitosamente",
            content = @Content(schema = @Schema(implementation = NotificacionesDTO.class)))
    @PostMapping
    public ResponseEntity<NotificacionesDTO> crear(@Valid @RequestBody NotificacionesDTO dto) {
        return ResponseEntity.status(201).body(service.crearNotificacion(dto));
    }

    @Operation(summary = "Actualizar una notificación")
    @ApiResponse(responseCode = "200", description = "Notificación actualizada exitosamente",
            content = @Content(schema = @Schema(implementation = NotificacionesDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionesDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionesDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        NotificacionesDTO actualizada = service.actualizarNotificacion(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

}
