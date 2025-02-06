package cl.somosafac.afacbackend.controller;

import com.AFAC_BackEnd.AFAC.DTO.MentoriasDTO;
import com.AFAC_BackEnd.AFAC.service.MentoriasService;
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
@RequestMapping("/api/mentorias")
@Validated
@Tag(name = "Mentorías", description = "Operaciones para la gestión de mentorías")
public class MentoriasController {

    private final MentoriasService service;

    public MentoriasController(MentoriasService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todas las mentorías")
    @ApiResponse(responseCode = "200", description = "Mentorías obtenidas exitosamente")
    @GetMapping
    public ResponseEntity<List<MentoriasDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodasLasMentorias());
    }

    @Operation(summary = "Obtener una mentoría por ID")
    @ApiResponse(responseCode = "200", description = "Mentoría obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = MentoriasDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<MentoriasDTO> obtenerPorId(@PathVariable Long id) {
        MentoriasDTO dto = service.obtenerMentoriaPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva mentoría")
    @ApiResponse(responseCode = "201", description = "Mentoría creada exitosamente",
            content = @Content(schema = @Schema(implementation = MentoriasDTO.class)))
    @PostMapping
    public ResponseEntity<MentoriasDTO> crear(@Valid @RequestBody MentoriasDTO dto) {
        return ResponseEntity.status(201).body(service.crearMentoria(dto));
    }

    @Operation(summary = "Actualizar una mentoría")
    @ApiResponse(responseCode = "200", description = "Mentoría actualizada exitosamente",
            content = @Content(schema = @Schema(implementation = MentoriasDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<MentoriasDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MentoriasDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        MentoriasDTO actualizada = service.actualizarMentoria(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una mentoría")
    @ApiResponse(responseCode = "200", description = "Mentoría eliminada exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarMentoria(id);
        return ResponseEntity.ok().build();
    }
}
