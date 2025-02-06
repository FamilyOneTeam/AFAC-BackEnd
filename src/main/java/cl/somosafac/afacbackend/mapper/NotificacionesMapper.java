package com.AFAC_BackEnd.AFAC.mapper;

import com.AFAC_BackEnd.AFAC.DTO.NotificacionesDTO;
import com.AFAC_BackEnd.AFAC.entity.NotificacionesEntity;
import com.AFAC_BackEnd.AFAC.entity.UsuarioEntity;
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