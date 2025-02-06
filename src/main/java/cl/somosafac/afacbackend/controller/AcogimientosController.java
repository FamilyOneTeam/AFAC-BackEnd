package cl.somosafac.afacbackend.controller;

import com.AFAC_BackEnd.AFAC.DTO.AcogimientosDTO;
import com.AFAC_BackEnd.AFAC.service.AcogimientosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/acogimientos")
@Validated
@Tag(name = "Acogimientos", description = "Operaciones para la gestión de acogimientos")
public class AcogimientosController {

    private final AcogimientosService service;

    public AcogimientosController(AcogimientosService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todos los acogimientos", description = "Devuelve todas las fichas de acogimientos registradas en la base de datos")
    @ApiResponse(responseCode = "200", description = "Listado de acogimientos obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<AcogimientosDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodosLosAcogimientos());
    }

    @Operation(summary = "Obtener un acogimiento por ID", description = "Obtiene una ficha de acogimiento específica a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acogimiento encontrado exitosamente", content = @Content(schema = @Schema(implementation = AcogimientosDTO.class))),
            @ApiResponse(responseCode = "404", description = "Acogimiento no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AcogimientosDTO> obtenerPorId(@PathVariable Long id) {
        var dto = service.obtenerAcogimientoPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo acogimiento", description = "Crea un nuevo registro de acogimiento en la base de datos")
    @ApiResponse(responseCode = "201", description = "Acogimiento creado exitosamente", content = @Content(schema = @Schema(implementation = AcogimientosDTO.class)))
    @PostMapping
    public ResponseEntity<AcogimientosDTO> crearAcogimiento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Detalles del nuevo acogimiento", required = true,
                    content = @Content(schema = @Schema(implementation = AcogimientosDTO.class)))
            @Valid @RequestBody AcogimientosDTO dto) {
        var creado = service.crearAcogimiento(dto);
        return ResponseEntity.status(201).body(creado);
    }

    @Operation(summary = "Actualizar un acogimiento", description = "Actualiza un registro existente de acogimiento en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acogimiento actualizado exitosamente", content = @Content(schema = @Schema(implementation = AcogimientosDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta (IDs no coinciden)"),
            @ApiResponse(responseCode = "404", description = "Acogimiento no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AcogimientosDTO> actualizarAcogimiento(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Detalles del acogimiento actualizado", required = true,
                    content = @Content(schema = @Schema(implementation = AcogimientosDTO.class)))
            @Valid @RequestBody AcogimientosDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        var actualizado = service.actualizarAcogimiento(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un acogimiento", description = "Elimina un registro de acogimiento a partir de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acogimiento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Acogimiento no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAcogimiento(@PathVariable Long id) {
        service.eliminarAcogimiento(id);
        return ResponseEntity.ok().build();
    }
}
