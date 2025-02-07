
package cl.somosafac.afacbackend.repository;


import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionesEntity, Long> {
    List<NotificacionesEntity> findByUsuarioId(Long usuarioId);
}
