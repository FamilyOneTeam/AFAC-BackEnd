package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.MentoriasDTO;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.MentoriasEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class MentoriasMapperImpl implements MentoriasMapper {

    @Override
    public MentoriasDTO toDTO(MentoriasEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MentoriasDTO mentoriasDTO = new MentoriasDTO();

        mentoriasDTO.setFamiliaMentoraId( entityFamiliaMentoraId( entity ) );
        mentoriasDTO.setFamiliaMentoradaId( entityFamiliaMentoradaId( entity ) );
        mentoriasDTO.setId( entity.getId() );
        mentoriasDTO.setFechaAsignacion( entity.getFechaAsignacion() );
        mentoriasDTO.setEstadoMentoria( entity.getEstadoMentoria() );

        return mentoriasDTO;
    }

    @Override
    public MentoriasEntity toEntity(MentoriasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MentoriasEntity.MentoriasEntityBuilder mentoriasEntity = MentoriasEntity.builder();

        mentoriasEntity.familiaMentora( mapFamiliaId( dto.getFamiliaMentoraId() ) );
        mentoriasEntity.familiaMentorada( mapFamiliaId( dto.getFamiliaMentoradaId() ) );
        mentoriasEntity.id( dto.getId() );
        mentoriasEntity.fechaAsignacion( dto.getFechaAsignacion() );
        mentoriasEntity.estadoMentoria( dto.getEstadoMentoria() );

        return mentoriasEntity.build();
    }

    private Long entityFamiliaMentoraId(MentoriasEntity mentoriasEntity) {
        FichaFamiliasEntity familiaMentora = mentoriasEntity.getFamiliaMentora();
        if ( familiaMentora == null ) {
            return null;
        }
        return familiaMentora.getId();
    }

    private Long entityFamiliaMentoradaId(MentoriasEntity mentoriasEntity) {
        FichaFamiliasEntity familiaMentorada = mentoriasEntity.getFamiliaMentorada();
        if ( familiaMentorada == null ) {
            return null;
        }
        return familiaMentorada.getId();
    }
}
