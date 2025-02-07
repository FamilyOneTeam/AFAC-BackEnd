package cl.somosafac.afacbackend.service;


import cl.somosafac.afacbackend.DTO.AcogimientosDTO;

import java.util.List;

public interface AcogimientosService {
    List<AcogimientosDTO> obtenerTodosLosAcogimientos();

    AcogimientosDTO obtenerAcogimientoPorId(Long id);

    AcogimientosDTO crearAcogimiento(AcogimientosDTO dto);

    AcogimientosDTO actualizarAcogimiento(Long id, AcogimientosDTO dto);

    void eliminarAcogimiento(Long id);
}
