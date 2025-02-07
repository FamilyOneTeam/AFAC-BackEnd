package cl.somosafac.afacbackend.controller;


import cl.somosafac.afacbackend.DTO.NotasDTO;
import cl.somosafac.afacbackend.service.NotasService;
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
@RequestMapping("/api/notas")
@Validated
@Tag(name = "Notas", description = "Operaciones para la gestión de notas")
public class NotasController {

    private final NotasService service;

    public NotasController(NotasService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todas las notas")
    @ApiResponse(responseCode = "200", description = "Notas obtenidas exitosamente")
    @GetMapping
    public ResponseEntity<List<NotasDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodasLasNotas());
    }

    @Operation(summary = "Obtener una nota por ID")
    @ApiResponse(responseCode = "200", description = "Nota obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = NotasDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<NotasDTO> obtenerPorId(@PathVariable Long id) {
        NotasDTO dto = service.obtenerNotaPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva nota")
    @ApiResponse(responseCode = "201", description = "Nota creada exitosamente",
            content = @Content(schema = @Schema(implementation = NotasDTO.class)))
    @PostMapping
    public ResponseEntity<NotasDTO> crear(@Valid @RequestBody NotasDTO dto) {
        return ResponseEntity.status(201).body(service.crearNota(dto));
    }

    @Operation(summary = "Actualizar una nota")
    @ApiResponse(responseCode = "200", description = "Nota actualizada exitosamente",
            content = @Content(schema = @Schema(implementation = NotasDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<NotasDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotasDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        NotasDTO actualizada = service.actualizarNota(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una nota")
    @ApiResponse(responseCode = "200", description = "Nota eliminada exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarNota(id);
        return ResponseEntity.ok().build();
    }
}
