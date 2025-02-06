package com.AFAC_BackEnd.AFAC.repository;

import com.AFAC_BackEnd.AFAC.entity.NotasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotasEntity, Long> {
    List<NotasEntity> findByFamilia_Id(Long familiaId);

}