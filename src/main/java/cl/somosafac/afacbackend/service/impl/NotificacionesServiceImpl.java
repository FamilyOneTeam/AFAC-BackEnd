package cl.somosafac.afacbackend.service.impl;

import cl.somosafac.afacbackend.DTO.NotificacionesDTO;
import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import cl.somosafac.afacbackend.mapper.NotificacionesMapper;
import cl.somosafac.afacbackend.repository.NotificacionRepository;
import cl.somosafac.afacbackend.service.NotificacionesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NotificacionesServiceImpl implements NotificacionesService {

    private final NotificacionRepository repository;
    private final NotificacionesMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionesDTO> obtenerTodasLasNotificaciones() {
        log.debug("Obteniendo todas las notificaciones");
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NotificacionesDTO obtenerNotificacionPorId(Long id) {
        log.debug("Buscando notificación con ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));
    }

    @Override
    public NotificacionesDTO crearNotificacion(NotificacionesDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula");
        }

        log.debug("Creando nueva notificación para usuario ID: {}", dto.getUsuarioId());

        validarNotificacion(dto);

        NotificacionesEntity entity = mapper.toEntity(dto);
        entity.setFechaEnvio(LocalDateTime.now());

        NotificacionesEntity savedEntity = repository.save(entity);
        log.info("Notificación creada con ID: {}", savedEntity.getId());

        return mapper.toDTO(savedEntity);
    }

    @Override
    public NotificacionesDTO actualizarNotificacion(Long id, NotificacionesDTO dto) {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos de notificación son requeridos");
        }

        log.debug("Actualizando notificación con ID: {}", id);

        NotificacionesEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));

        validarNotificacion(dto);

        // Preservar datos importantes
        NotificacionesEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id);
        updatedEntity.setFechaEnvio(existingEntity.getFechaEnvio());

        NotificacionesEntity savedEntity = repository.save(updatedEntity);
        log.info("Notificación actualizada con ID: {}", savedEntity.getId());

        return mapper.toDTO(savedEntity);
    }

    private void validarNotificacion(NotificacionesDTO dto) {
        if (dto.getUsuarioId() == null) {
            throw new IllegalArgumentException("El ID del usuario es requerido");
        }
        if (dto.getMensaje() == null || dto.getMensaje().trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío");
        }
        if (dto.getTipoNotificacion() == null || dto.getTipoNotificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de notificación es requerido");
        }
    }
}