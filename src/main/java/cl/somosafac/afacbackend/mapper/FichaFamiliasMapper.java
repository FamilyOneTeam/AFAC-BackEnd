package cl.somosafac.afacbackend.mapper;

import com.AFAC_BackEnd.AFAC.DTO.FichaFamiliasDTO;
import com.AFAC_BackEnd.AFAC.entity.FichaFamiliasEntity;
import com.AFAC_BackEnd.AFAC.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface FichaFamiliasMapper {
    @Mapping(source = "usuario.id", target = "usuarioId")
    FichaFamiliasDTO toDTO(FichaFamiliasEntity entity);

    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "mapId")
    FichaFamiliasEntity toEntity(FichaFamiliasDTO dto);

    List<FichaFamiliasDTO> toDTOList(List<FichaFamiliasEntity> entities);

    @Named("mapId")
    default UsuarioEntity mapId(Long id) {
        if (id == null) return null;
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(id);
        return usuario;
    }
}