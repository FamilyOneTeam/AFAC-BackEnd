package cl.somosafac.afacbackend.service.impl;


import cl.somosafac.afacbackend.DTO.NotificacionesDTO;
import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import cl.somosafac.afacbackend.mapper.NotificacionesMapper;
import cl.somosafac.afacbackend.repository.NotificacionRepository;
import cl.somosafac.afacbackend.service.NotificacionesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificacionesServiceImpl implements NotificacionesService {

    private final NotificacionRepository repository;
    private final NotificacionesMapper mapper;

    public NotificacionesServiceImpl(NotificacionRepository repository, NotificacionesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<NotificacionesDTO> obtenerTodasLasNotificaciones() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificacionesDTO obtenerNotificacionPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public NotificacionesDTO crearNotificacion(NotificacionesDTO dto) {
        NotificacionesEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public NotificacionesDTO actualizarNotificacion(Long id, NotificacionesDTO dto) {
        NotificacionesEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null;
        }
        NotificacionesEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        return mapper.toDTO(repository.save(updatedEntity));
    }

}