
package com.AFAC_BackEnd.AFAC.repository;

import com.AFAC_BackEnd.AFAC.entity.FichaFamiliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaFamiliaRepository extends JpaRepository<FichaFamiliasEntity, Long> {

}
