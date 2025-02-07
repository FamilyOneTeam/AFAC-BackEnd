package cl.somosafac.afacbackend.mapper;


import cl.somosafac.afacbackend.DTO.FichaInfanteDTO;
import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FichaInfanteMapper {

    FichaInfanteDTO toDTO(FichaInfanteEntity entity);
    FichaInfanteEntity toEntity(FichaInfanteDTO dto);
}