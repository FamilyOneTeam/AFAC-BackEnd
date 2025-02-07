package cl.somosafac.afacbackend.mapper;


import cl.somosafac.afacbackend.DTO.NotasDTO;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.NotasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;

@Mapper(componentModel = "spring", uses = FichaFamiliasMapper.class)
public interface NotasMapper {
    @Mapping(source = "familia.id", target = "familiaId")
    NotasDTO toDTO(NotasEntity entity);

    @Mapping(source = "familiaId", target = "familia", qualifiedByName = "idToFamiliaEntity")
    NotasEntity toEntity(NotasDTO dto);

    List<NotasDTO> toDTOList(List<NotasEntity> entities);

    @Named("idToFamiliaEntity")
    default FichaFamiliasEntity idToFamiliaEntity(Long id) {
        if (id == null) return null;
        FichaFamiliasEntity familia = new FichaFamiliasEntity();
        familia.setId(id);
        return familia;
    }
}