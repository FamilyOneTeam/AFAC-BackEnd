package cl.somosafac.afacbackend.service.impl;


import cl.somosafac.afacbackend.DTO.FichaInfanteDTO;
import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import cl.somosafac.afacbackend.mapper.FichaInfanteMapper;
import cl.somosafac.afacbackend.repository.FichaInfanteRepository;
import cl.somosafac.afacbackend.service.FichaInfanteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FichaInfanteServiceImpl implements FichaInfanteService {

    private final FichaInfanteRepository repository;
    private final FichaInfanteMapper mapper;

    public FichaInfanteServiceImpl(FichaInfanteRepository repository, FichaInfanteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<FichaInfanteDTO> obtenerTodosLosInfantes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FichaInfanteDTO obtenerInfantePorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public FichaInfanteDTO crearInfante(FichaInfanteDTO dto) {
        FichaInfanteEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public FichaInfanteDTO actualizarInfante(Long id, FichaInfanteDTO dto) {
        FichaInfanteEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null;
        }
        FichaInfanteEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        return mapper.toDTO(repository.save(updatedEntity));
    }

    @Override
    public void eliminarInfante(Long id) {
        repository.deleteById(id);
    }
}
