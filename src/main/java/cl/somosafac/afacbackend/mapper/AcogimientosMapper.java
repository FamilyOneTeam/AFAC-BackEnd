package cl.somosafac.afacbackend.mapper;


import cl.somosafac.afacbackend.DTO.AcogimientosDTO;
import cl.somosafac.afacbackend.entity.AcogimientosEntity;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AcogimientosMapper {
    @Mapping(source = "familia.id", target = "familiaId")
    @Mapping(source = "infante.id", target = "infanteId")
    AcogimientosDTO toDTO(AcogimientosEntity entity);

    @Mapping(source = "familiaId", target = "familia", qualifiedByName = "mapFamiliaId")
    @Mapping(source = "infanteId", target = "infante", qualifiedByName = "mapInfanteId")
    AcogimientosEntity toEntity(AcogimientosDTO dto);

    @Named("mapFamiliaId")
    default FichaFamiliasEntity mapFamiliaId(Long id) {
        if (id == null) return null;
        FichaFamiliasEntity familia = new FichaFamiliasEntity();
        familia.setId(id);
        return familia;
    }

    @Named("mapInfanteId")
    default FichaInfanteEntity mapInfanteId(Long id) {
        if (id == null) return null;
        FichaInfanteEntity infante = new FichaInfanteEntity();
        infante.setId(id);
        return infante;
    }
}