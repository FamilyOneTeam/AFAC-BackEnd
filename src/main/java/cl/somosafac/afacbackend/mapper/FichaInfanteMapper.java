package com.AFAC_BackEnd.AFAC.mapper;

import com.AFAC_BackEnd.AFAC.DTO.FichaInfanteDTO;
import com.AFAC_BackEnd.AFAC.entity.FichaInfanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FichaInfanteMapper {

    FichaInfanteDTO toDTO(FichaInfanteEntity entity);
    FichaInfanteEntity toEntity(FichaInfanteDTO dto);
}