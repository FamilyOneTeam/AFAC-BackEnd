package cl.somosafac.afacbackend.service;


import cl.somosafac.afacbackend.DTO.FichaInfanteDTO;

import java.util.List;

public interface FichaInfanteService {

    List<FichaInfanteDTO> obtenerTodosLosInfantes();

    FichaInfanteDTO obtenerInfantePorId(Long id);

    FichaInfanteDTO crearInfante(FichaInfanteDTO infante);

    FichaInfanteDTO actualizarInfante(Long id, FichaInfanteDTO infante);

    void eliminarInfante(Long id);
}