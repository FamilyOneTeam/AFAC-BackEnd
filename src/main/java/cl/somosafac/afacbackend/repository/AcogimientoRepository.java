package cl.somosafac.afacbackend.repository;

import cl.somosafac.afacbackend.entity.AcogimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcogimientoRepository extends JpaRepository<AcogimientosEntity, Long> {

    List<AcogimientosEntity> findByFamiliaId(Long familiaId);

    List<AcogimientosEntity> findByInfanteId(Long infanteId);
}