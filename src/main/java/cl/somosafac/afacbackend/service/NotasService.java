package cl.somosafac.afacbackend.service;


import cl.somosafac.afacbackend.DTO.NotasDTO;

import java.util.List;

public interface NotasService {

    List<NotasDTO> obtenerTodasLasNotas();

    NotasDTO obtenerNotaPorId(Long id);

    NotasDTO crearNota(NotasDTO nota);

    NotasDTO actualizarNota(Long id, NotasDTO nota);

    void eliminarNota(Long id);
}