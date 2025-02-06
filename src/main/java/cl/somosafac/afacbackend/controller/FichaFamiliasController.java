package com.AFAC_BackEnd.AFAC.controller;

import com.AFAC_BackEnd.AFAC.DTO.FichaFamiliasDTO;
import com.AFAC_BackEnd.AFAC.service.FichaFamiliasService;
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
@RequestMapping("/api/fichas-familias")
@Validated
@Tag(name = "Ficha Familias", description = "Operaciones para la gestión de fichas de familias")
public class FichaFamiliasController {

    private final FichaFamiliasService service;

    public FichaFamiliasController(FichaFamiliasService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todas las fichas de familias")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    @GetMapping
    public ResponseEntity<List<FichaFamiliasDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodasLasFamilias());
    }

    @Operation(summary = "Obtener una ficha de familia por ID")
    @ApiResponse(responseCode = "200", description = "Ficha obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = FichaFamiliasDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<FichaFamiliasDTO> obtenerPorId(@PathVariable Long id) {
        FichaFamiliasDTO dto = service.obtenerFamiliaPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva ficha de familia")
    @ApiResponse(responseCode = "201", description = "Ficha creada exitosamente",
            content = @Content(schema = @Schema(implementation = FichaFamiliasDTO.class)))
    @PostMapping
    public ResponseEntity<FichaFamiliasDTO> crear(@Valid @RequestBody FichaFamiliasDTO dto) {
        return ResponseEntity.status(201).body(service.crearFamilia(dto));
    }

    @Operation(summary = "Actualizar una ficha de familia")
    @ApiResponse(responseCode = "200", description = "Ficha actualizada exitosamente",
            content = @Content(schema = @Schema(implementation = FichaFamiliasDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<FichaFamiliasDTO> actualizar(@PathVariable Long id, @Valid @RequestBody FichaFamiliasDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        FichaFamiliasDTO actualizada = service.actualizarFamilia(id, dto);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una ficha de familia")
    @ApiResponse(responseCode = "200", description = "Ficha eliminada exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarFamilia(id);
        return ResponseEntity.ok().build();
    }
}
