package cl.somosafac.afacbackend.service.impl;

import com.AFAC_BackEnd.AFAC.DTO.FichaFamiliasDTO;
import com.AFAC_BackEnd.AFAC.entity.FichaFamiliasEntity;
import com.AFAC_BackEnd.AFAC.mapper.FichaFamiliasMapper;
import com.AFAC_BackEnd.AFAC.repository.FichaFamiliaRepository;
import com.AFAC_BackEnd.AFAC.service.FichaFamiliasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FichaFamiliasServiceImpl implements FichaFamiliasService {

    private final FichaFamiliaRepository repository;
    private final FichaFamiliasMapper mapper;

    public FichaFamiliasServiceImpl(FichaFamiliaRepository repository, FichaFamiliasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<FichaFamiliasDTO> obtenerTodasLasFamilias() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FichaFamiliasDTO obtenerFamiliaPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public FichaFamiliasDTO crearFamilia(FichaFamiliasDTO dto) {
        FichaFamiliasEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public FichaFamiliasDTO actualizarFamilia(Long id, FichaFamiliasDTO dto) {
        FichaFamiliasEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null;
        }
        FichaFamiliasEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        return mapper.toDTO(repository.save(updatedEntity));
    }

    @Override
    public void eliminarFamilia(Long id) {
        repository.deleteById(id);
    }
}
