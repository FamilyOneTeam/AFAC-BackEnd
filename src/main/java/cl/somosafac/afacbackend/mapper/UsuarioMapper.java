package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleToString")
    @Mapping(source = "tipoUsuario", target = "tipoUsuario", qualifiedByName = "roleToString")
    UsuarioDTO toDTO(UsuarioEntity entity);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringToRole")
    @Mapping(source = "tipoUsuario", target = "tipoUsuario", qualifiedByName = "stringToRole")
    @Mapping(target = "contrasenaHash", ignore = true)
    @Mapping(target = "resetToken", ignore = true)
    @Mapping(target = "resetTokenExpiry", ignore = true)
    UsuarioEntity toEntity(UsuarioDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrasenaHash", ignore = true)
    @Mapping(target = "resetToken", ignore = true)
    @Mapping(target = "resetTokenExpiry", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringToRole")
    @Mapping(source = "tipoUsuario", target = "tipoUsuario", qualifiedByName = "stringToRole")
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget UsuarioEntity entity);

    List<UsuarioDTO> toDTOList(List<UsuarioEntity> entities);

    @Named("roleToString")
    default String roleToString(Role role) {
        return role != null ? role.name() : null;
    }

    @Named("stringToRole")
    default Role stringToRole(String role) {
        return role != null ? Role.valueOf(role) : null;
    }

    @Named("roleSetToString")
    default Set<String> roleToString(Set<Role> roles) {
        return roles != null ? roles.stream()
                .map(Role::name)
                .collect(Collectors.toSet()) : new HashSet<>();
    }

    @Named("stringToRoleSet")
    default Set<Role> stringToRole(Set<String> roles) {
        return roles != null ? roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet()) : new HashSet<>();
    }
}