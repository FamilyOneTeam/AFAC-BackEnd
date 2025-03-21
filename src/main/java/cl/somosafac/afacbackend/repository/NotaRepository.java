package cl.somosafac.afacbackend.repository;


import cl.somosafac.afacbackend.entity.NotasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotasEntity, Long> {
    List<NotasEntity> findByFamilia_Id(Long familiaId);

}