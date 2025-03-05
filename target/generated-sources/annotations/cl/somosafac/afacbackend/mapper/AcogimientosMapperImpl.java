package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.AcogimientosDTO;
import cl.somosafac.afacbackend.entity.AcogimientosEntity;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AcogimientosMapperImpl implements AcogimientosMapper {

    @Override
    public AcogimientosDTO toDTO(AcogimientosEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AcogimientosDTO acogimientosDTO = new AcogimientosDTO();

        acogimientosDTO.setFamiliaId( entityFamiliaId( entity ) );
        acogimientosDTO.setInfanteId( entityInfanteId( entity ) );
        acogimientosDTO.setId( entity.getId() );
        acogimientosDTO.setFechaInicio( entity.getFechaInicio() );
        acogimientosDTO.setFechaFin( entity.getFechaFin() );
        acogimientosDTO.setEstadoAcogimiento( entity.getEstadoAcogimiento() );

        return acogimientosDTO;
    }

    @Override
    public AcogimientosEntity toEntity(AcogimientosDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AcogimientosEntity.AcogimientosEntityBuilder acogimientosEntity = AcogimientosEntity.builder();

        acogimientosEntity.familia( mapFamiliaId( dto.getFamiliaId() ) );
        acogimientosEntity.infante( mapInfanteId( dto.getInfanteId() ) );
        acogimientosEntity.id( dto.getId() );
        acogimientosEntity.fechaInicio( dto.getFechaInicio() );
        acogimientosEntity.fechaFin( dto.getFechaFin() );
        acogimientosEntity.estadoAcogimiento( dto.getEstadoAcogimiento() );

        return acogimientosEntity.build();
    }

    private Long entityFamiliaId(AcogimientosEntity acogimientosEntity) {
        FichaFamiliasEntity familia = acogimientosEntity.getFamilia();
        if ( familia == null ) {
            return null;
        }
        return familia.getId();
    }

    private Long entityInfanteId(AcogimientosEntity acogimientosEntity) {
        FichaInfanteEntity infante = acogimientosEntity.getInfante();
        if ( infante == null ) {
            return null;
        }
        return infante.getId();
    }
}
