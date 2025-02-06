package com.AFAC_BackEnd.AFAC.mapper;

import com.AFAC_BackEnd.AFAC.DTO.UsuarioDTO;
import com.AFAC_BackEnd.AFAC.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.AFAC_BackEnd.AFAC.security.Role;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // When converting to DTO, we don't need to ignore contrasenaHash because it's not in the DTO
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleToString")
    UsuarioDTO toDTO(UsuarioEntity entity);

    // When converting to Entity, we need to ignore contrasenaHash to prevent overwriting it
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringToRole")
    @Mapping(target = "contrasenaHash", ignore = true)
    UsuarioEntity toEntity(UsuarioDTO dto);

    // When updating, we ignore both id and password hash
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrasenaHash", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringToRole")
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget UsuarioEntity entity);

    List<UsuarioDTO> toDTOList(List<UsuarioEntity> entities);

    @Named("roleToString")
    default Set<String> roleToString(Set<Role> roles) {
        return roles != null ? roles.stream()
                .map(Role::name)
                .collect(Collectors.toSet()) : new HashSet<>();
    }

    @Named("stringToRole")
    default Set<Role> stringToRole(Set<String> roles) {
        return roles != null ? roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet()) : new HashSet<>();
    }

    @Named("idToUsuario")
    default UsuarioEntity idToUsuario(Long id) {
        if (id == null) return null;
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(id);
        return usuario;
    }
}