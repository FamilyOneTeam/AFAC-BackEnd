package cl.somosafac.afacbackend.mapper;


import cl.somosafac.afacbackend.DTO.NotificacionesDTO;
import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface NotificacionesMapper {
    @Mapping(source = "usuario.id", target = "usuarioId")
    NotificacionesDTO toDTO(NotificacionesEntity entity);

    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "idToUsuario")
    NotificacionesEntity toEntity(NotificacionesDTO dto);
}