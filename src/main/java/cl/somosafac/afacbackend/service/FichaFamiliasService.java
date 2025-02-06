package cl.somosafac.afacbackend.service;

import com.AFAC_BackEnd.AFAC.DTO.FichaFamiliasDTO;
import java.util.List;

public interface FichaFamiliasService {

    List<FichaFamiliasDTO> obtenerTodasLasFamilias();

    FichaFamiliasDTO obtenerFamiliaPorId(Long id);

    FichaFamiliasDTO crearFamilia(FichaFamiliasDTO familia);

    FichaFamiliasDTO actualizarFamilia(Long id, FichaFamiliasDTO familia);

    void eliminarFamilia(Long id);
}