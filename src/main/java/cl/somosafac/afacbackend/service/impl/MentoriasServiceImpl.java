package cl.somosafac.afacbackend.service.impl;


import cl.somosafac.afacbackend.DTO.MentoriasDTO;
import cl.somosafac.afacbackend.entity.MentoriasEntity;
import cl.somosafac.afacbackend.mapper.MentoriasMapper;
import cl.somosafac.afacbackend.repository.MentoriaRepository;
import cl.somosafac.afacbackend.service.MentoriasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MentoriasServiceImpl implements MentoriasService {

    private final MentoriaRepository repository;
    private final MentoriasMapper mapper;

    public MentoriasServiceImpl(MentoriaRepository repository, MentoriasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MentoriasDTO> obtenerTodasLasMentorias() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MentoriasDTO obtenerMentoriaPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public MentoriasDTO crearMentoria(MentoriasDTO dto) {
        MentoriasEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public MentoriasDTO actualizarMentoria(Long id, MentoriasDTO dto) {
        MentoriasEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null;
        }
        MentoriasEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        return mapper.toDTO(repository.save(updatedEntity));
    }

    @Override
    public void eliminarMentoria(Long id) {
        repository.deleteById(id);
    }
}

