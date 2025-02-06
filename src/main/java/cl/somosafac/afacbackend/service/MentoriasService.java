package cl.somosafac.afacbackend.service;

import com.AFAC_BackEnd.AFAC.DTO.MentoriasDTO;
import java.util.List;

public interface MentoriasService {

    List<MentoriasDTO> obtenerTodasLasMentorias();

    MentoriasDTO obtenerMentoriaPorId(Long id);

    MentoriasDTO crearMentoria(MentoriasDTO mentoria);

    MentoriasDTO actualizarMentoria(Long id, MentoriasDTO mentoria);

    void eliminarMentoria(Long id);
}
