package cl.somosafac.afacbackend.service.impl;


import cl.somosafac.afacbackend.DTO.NotasDTO;
import cl.somosafac.afacbackend.entity.NotasEntity;
import cl.somosafac.afacbackend.mapper.NotasMapper;
import cl.somosafac.afacbackend.repository.NotaRepository;
import cl.somosafac.afacbackend.service.NotasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotasServiceImpl implements NotasService {

    private final NotaRepository repository;
    private final NotasMapper mapper;

    public NotasServiceImpl(NotaRepository repository, NotasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<NotasDTO> obtenerTodasLasNotas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotasDTO obtenerNotaPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public NotasDTO crearNota(NotasDTO dto) {
        NotasEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public NotasDTO actualizarNota(Long id, NotasDTO dto) {
        NotasEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null;
        }
        NotasEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        return mapper.toDTO(repository.save(updatedEntity));
    }

    @Override
    public void eliminarNota(Long id) {
        repository.deleteById(id);
    }
}