package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.NotificacionesDTO;
import cl.somosafac.afacbackend.entity.NotificacionesEntity;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class NotificacionesMapperImpl implements NotificacionesMapper {

    @Override
    public NotificacionesDTO toDTO(NotificacionesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NotificacionesDTO notificacionesDTO = new NotificacionesDTO();

        notificacionesDTO.setUsuarioId( entityUsuarioId( entity ) );
        notificacionesDTO.setId( entity.getId() );
        notificacionesDTO.setMensaje( entity.getMensaje() );
        notificacionesDTO.setFechaEnvio( entity.getFechaEnvio() );
        notificacionesDTO.setTipoNotificacion( entity.getTipoNotificacion() );

        return notificacionesDTO;
    }

    @Override
    public NotificacionesEntity toEntity(NotificacionesDTO dto) {
        if ( dto == null ) {
            return null;
        }

        NotificacionesEntity.NotificacionesEntityBuilder notificacionesEntity = NotificacionesEntity.builder();

        notificacionesEntity.usuario( idToUsuario( dto.getUsuarioId() ) );
        notificacionesEntity.id( dto.getId() );
        notificacionesEntity.mensaje( dto.getMensaje() );
        notificacionesEntity.fechaEnvio( dto.getFechaEnvio() );
        notificacionesEntity.tipoNotificacion( dto.getTipoNotificacion() );

        return notificacionesEntity.build();
    }

    @Override
    public void updateEntityFromDto(NotificacionesDTO dto, NotificacionesEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUsuarioId() != null ) {
            entity.setUsuario( idToUsuario( dto.getUsuarioId() ) );
        }
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getMensaje() != null ) {
            entity.setMensaje( dto.getMensaje() );
        }
        if ( dto.getFechaEnvio() != null ) {
            entity.setFechaEnvio( dto.getFechaEnvio() );
        }
        if ( dto.getTipoNotificacion() != null ) {
            entity.setTipoNotificacion( dto.getTipoNotificacion() );
        }
    }

    private Long entityUsuarioId(NotificacionesEntity notificacionesEntity) {
        UsuarioEntity usuario = notificacionesEntity.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
