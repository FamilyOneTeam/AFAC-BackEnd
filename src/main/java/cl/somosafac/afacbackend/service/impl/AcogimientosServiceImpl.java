package cl.somosafac.afacbackend.service.impl;


import cl.somosafac.afacbackend.DTO.AcogimientosDTO;
import cl.somosafac.afacbackend.entity.AcogimientosEntity;
import cl.somosafac.afacbackend.mapper.AcogimientosMapper;
import cl.somosafac.afacbackend.repository.AcogimientoRepository;
import cl.somosafac.afacbackend.service.AcogimientosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcogimientosServiceImpl implements AcogimientosService {

    private final AcogimientoRepository repository;
    private final AcogimientosMapper mapper;

    public AcogimientosServiceImpl(AcogimientoRepository repository, AcogimientosMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AcogimientosDTO> obtenerTodosLosAcogimientos() {
        // Lógica de negocio: Convertir entidades a DTOs
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AcogimientosDTO obtenerAcogimientoPorId(Long id) {
        // Lógica de negocio: Manejar el caso de entidad no encontrada
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public AcogimientosDTO crearAcogimiento(AcogimientosDTO dto) {
        // Lógica de negocio: Validar datos y guardar entidad
        AcogimientosEntity entity = mapper.toEntity(dto);
        AcogimientosEntity savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public AcogimientosDTO actualizarAcogimiento(Long id, AcogimientosDTO dto) {
        // Lógica de negocio: Validar existencia antes de actualizar
        AcogimientosEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            return null; // O lanzar una excepción personalizada
        }

        // Actualizar campos de la entidad
        AcogimientosEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id); // Asegurar que el ID se mantiene
        return mapper.toDTO(repository.save(updatedEntity));
    }

    @Override
    public void eliminarAcogimiento(Long id) {
        // Lógica de negocio: Validar existencia antes de eliminar
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
