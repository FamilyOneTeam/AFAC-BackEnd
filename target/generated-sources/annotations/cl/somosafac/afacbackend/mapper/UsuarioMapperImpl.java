package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.security.Role;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(UsuarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setRoles( roleSetToStringSet( entity.getRoles() ) );
        usuarioDTO.setTipoUsuario( roleToString( entity.getTipoUsuario() ) );
        usuarioDTO.setId( entity.getId() );
        usuarioDTO.setNombre( entity.getNombre() );
        usuarioDTO.setApellido( entity.getApellido() );
        usuarioDTO.setCorreo( entity.getCorreo() );
        usuarioDTO.setCargo( entity.getCargo() );
        usuarioDTO.setActivo( entity.getActivo() );
        usuarioDTO.setVerificado( entity.getVerificado() );
        usuarioDTO.setFechaRegistro( entity.getFechaRegistro() );
        usuarioDTO.setFechaUltimoAcceso( entity.getFechaUltimoAcceso() );
        usuarioDTO.setAceptarTerminos( entity.getAceptarTerminos() );

        return usuarioDTO;
    }

    @Override
    public UsuarioEntity toEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UsuarioEntity.UsuarioEntityBuilder usuarioEntity = UsuarioEntity.builder();

        usuarioEntity.roles( stringSetToRoleSet( dto.getRoles() ) );
        usuarioEntity.tipoUsuario( stringToRole( dto.getTipoUsuario() ) );
        usuarioEntity.id( dto.getId() );
        usuarioEntity.nombre( dto.getNombre() );
        usuarioEntity.apellido( dto.getApellido() );
        usuarioEntity.correo( dto.getCorreo() );
        usuarioEntity.cargo( dto.getCargo() );
        usuarioEntity.activo( dto.getActivo() );
        usuarioEntity.verificado( dto.getVerificado() );
        usuarioEntity.fechaRegistro( dto.getFechaRegistro() );
        usuarioEntity.fechaUltimoAcceso( dto.getFechaUltimoAcceso() );
        usuarioEntity.aceptarTerminos( dto.getAceptarTerminos() );

        return usuarioEntity.build();
    }

    @Override
    public void updateEntityFromDTO(UsuarioDTO dto, UsuarioEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getRoles() != null ) {
            Set<Role> set = stringSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.getRoles().clear();
                entity.getRoles().addAll( set );
            }
            else {
                entity.setRoles( null );
            }
        }
        else {
            Set<Role> set = stringSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.setRoles( set );
            }
        }
        entity.setTipoUsuario( stringToRole( dto.getTipoUsuario() ) );
        entity.setNombre( dto.getNombre() );
        entity.setApellido( dto.getApellido() );
        entity.setCorreo( dto.getCorreo() );
        entity.setCargo( dto.getCargo() );
        entity.setActivo( dto.getActivo() );
        entity.setVerificado( dto.getVerificado() );
        entity.setFechaRegistro( dto.getFechaRegistro() );
        entity.setFechaUltimoAcceso( dto.getFechaUltimoAcceso() );
        entity.setAceptarTerminos( dto.getAceptarTerminos() );
    }

    @Override
    public List<UsuarioDTO> toDTOList(List<UsuarioEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( entities.size() );
        for ( UsuarioEntity usuarioEntity : entities ) {
            list.add( toDTO( usuarioEntity ) );
        }

        return list;
    }

    protected Set<String> roleSetToStringSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = new LinkedHashSet<String>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToString( role ) );
        }

        return set1;
    }

    protected Set<Role> stringSetToRoleSet(Set<String> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( String string : set ) {
            set1.add( stringToRole( string ) );
        }

        return set1;
    }
}
