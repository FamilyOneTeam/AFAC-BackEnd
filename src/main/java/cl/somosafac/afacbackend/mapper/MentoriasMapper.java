package com.AFAC_BackEnd.AFAC.mapper;

import com.AFAC_BackEnd.AFAC.DTO.MentoriasDTO;
import com.AFAC_BackEnd.AFAC.entity.MentoriasEntity;
import com.AFAC_BackEnd.AFAC.entity.FichaFamiliasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MentoriasMapper {

    @Mapping(source = "familiaMentora.id", target = "familiaMentoraId")
    @Mapping(source = "familiaMentorada.id", target = "familiaMentoradaId")
    MentoriasDTO toDTO(MentoriasEntity entity);

    @Mapping(source = "familiaMentoraId", target = "familiaMentora", qualifiedByName = "mapFamiliaId")
    @Mapping(source = "familiaMentoradaId", target = "familiaMentorada", qualifiedByName = "mapFamiliaId")
    MentoriasEntity toEntity(MentoriasDTO dto);

    @Named("mapFamiliaId")
    default FichaFamiliasEntity mapFamiliaId(Long id) {
        if (id == null) return null;
        FichaFamiliasEntity familia = new FichaFamiliasEntity();
        familia.setId(id);
        return familia;
    }
}