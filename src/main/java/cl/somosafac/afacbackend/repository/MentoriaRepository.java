
package cl.somosafac.afacbackend.repository;


import cl.somosafac.afacbackend.entity.MentoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoriaRepository extends JpaRepository<MentoriasEntity, Long> {
    List<MentoriasEntity> findByFamiliaMentoraId(Long familiaMentoraId);
    List<MentoriasEntity> findByFamiliaMentoradaId(Long familiaMentoradaId);
}
