
package cl.somosafac.afacbackend.repository;

import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaFamiliaRepository extends JpaRepository<FichaFamiliasEntity, Long> {

}
