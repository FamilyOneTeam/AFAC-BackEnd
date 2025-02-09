package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.NotasDTO;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.NotasEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class NotasMapperImpl implements NotasMapper {

    @Override
    public NotasDTO toDTO(NotasEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NotasDTO notasDTO = new NotasDTO();

        notasDTO.setFamiliaId( entityFamiliaId( entity ) );
        notasDTO.setId( entity.getId() );
        notasDTO.setDescripcion( entity.getDescripcion() );
        notasDTO.setFechaCreacion( entity.getFechaCreacion() );

        return notasDTO;
    }

    @Override
    public NotasEntity toEntity(NotasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        NotasEntity.NotasEntityBuilder notasEntity = NotasEntity.builder();

        notasEntity.familia( idToFamiliaEntity( dto.getFamiliaId() ) );
        notasEntity.id( dto.getId() );
        notasEntity.descripcion( dto.getDescripcion() );
        notasEntity.fechaCreacion( dto.getFechaCreacion() );

        return notasEntity.build();
    }

    @Override
    public List<NotasDTO> toDTOList(List<NotasEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<NotasDTO> list = new ArrayList<NotasDTO>( entities.size() );
        for ( NotasEntity notasEntity : entities ) {
            list.add( toDTO( notasEntity ) );
        }

        return list;
    }

    private Long entityFamiliaId(NotasEntity notasEntity) {
        FichaFamiliasEntity familia = notasEntity.getFamilia();
        if ( familia == null ) {
            return null;
        }
        return familia.getId();
    }
}
