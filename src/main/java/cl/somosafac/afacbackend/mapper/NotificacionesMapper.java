package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.NotificacionesDTO;
import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface NotificacionesMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    NotificacionesDTO toDTO(NotificacionesEntity entity);

    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "idToUsuario")
    NotificacionesEntity toEntity(NotificacionesDTO dto);

    @Named("idToUsuario")
    default UsuarioEntity idToUsuario(Long id) {
        if (id == null) return null;
        return UsuarioEntity.builder()
                .id(id)
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "idToUsuario")
    void updateEntityFromDto(NotificacionesDTO dto, @MappingTarget NotificacionesEntity entity);
}