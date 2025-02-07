package cl.somosafac.afacbackend.service;


import cl.somosafac.afacbackend.DTO.MentoriasDTO;

import java.util.List;

public interface MentoriasService {

    List<MentoriasDTO> obtenerTodasLasMentorias();

    MentoriasDTO obtenerMentoriaPorId(Long id);

    MentoriasDTO crearMentoria(MentoriasDTO mentoria);

    MentoriasDTO actualizarMentoria(Long id, MentoriasDTO mentoria);

    void eliminarMentoria(Long id);
}
