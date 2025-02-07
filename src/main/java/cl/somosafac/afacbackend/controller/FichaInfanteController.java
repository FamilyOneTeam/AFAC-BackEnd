package cl.somosafac.afacbackend.controller;

import cl.somosafac.afacbackend.DTO.FichaInfanteDTO;
import cl.somosafac.afacbackend.service.FichaInfanteService;
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
@RequestMapping("/api/infantes")
@Validated
@Tag(name = "Ficha Infantes", description = "Operaciones para la gestión de fichas de infantes")
public class FichaInfanteController {

    private final FichaInfanteService service;

    public FichaInfanteController(FichaInfanteService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todos los infantes")
    @ApiResponse(responseCode = "200", description = "Infantes obtenidos exitosamente")
    @GetMapping
    public ResponseEntity<List<FichaInfanteDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodosLosInfantes());
    }

    @Operation(summary = "Obtener un infante por ID")
    @ApiResponse(responseCode = "200", description = "Infante obtenido exitosamente",
            content = @Content(schema = @Schema(implementation = FichaInfanteDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<FichaInfanteDTO> obtenerPorId(@PathVariable Long id) {
        FichaInfanteDTO dto = service.obtenerInfantePorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo infante")
    @ApiResponse(responseCode = "201", description = "Infante creado exitosamente",
            content = @Content(schema = @Schema(implementation = FichaInfanteDTO.class)))
    @PostMapping
    public ResponseEntity<FichaInfanteDTO> crear(@Valid @RequestBody FichaInfanteDTO dto) {
        return ResponseEntity.status(201).body(service.crearInfante(dto));
    }

    @Operation(summary = "Actualizar un infante")
    @ApiResponse(responseCode = "200", description = "Infante actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = FichaInfanteDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<FichaInfanteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody FichaInfanteDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        FichaInfanteDTO actualizado = service.actualizarInfante(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

}
