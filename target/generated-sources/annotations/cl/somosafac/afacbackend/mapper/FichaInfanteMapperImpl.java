package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.FichaInfanteDTO;
import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class FichaInfanteMapperImpl implements FichaInfanteMapper {

    @Override
    public FichaInfanteDTO toDTO(FichaInfanteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        FichaInfanteDTO fichaInfanteDTO = new FichaInfanteDTO();

        fichaInfanteDTO.setId( entity.getId() );
        fichaInfanteDTO.setNombreInfante( entity.getNombreInfante() );
        fichaInfanteDTO.setRutInfante( entity.getRutInfante() );
        fichaInfanteDTO.setFechaNacimientoInfante( entity.getFechaNacimientoInfante() );
        fichaInfanteDTO.setRegion( entity.getRegion() );
        fichaInfanteDTO.setComuna( entity.getComuna() );
        fichaInfanteDTO.setDireccion( entity.getDireccion() );
        fichaInfanteDTO.setIngresoInfante( entity.getIngresoInfante() );
        fichaInfanteDTO.setEdadNna( entity.getEdadNna() );
        fichaInfanteDTO.setRangoEdadNna( entity.getRangoEdadNna() );
        fichaInfanteDTO.setSexoNna( entity.getSexoNna() );
        fichaInfanteDTO.setNacionalidadNna( entity.getNacionalidadNna() );
        fichaInfanteDTO.setIngresoAfac( entity.getIngresoAfac() );

        return fichaInfanteDTO;
    }

    @Override
    public FichaInfanteEntity toEntity(FichaInfanteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FichaInfanteEntity.FichaInfanteEntityBuilder fichaInfanteEntity = FichaInfanteEntity.builder();

        fichaInfanteEntity.id( dto.getId() );
        fichaInfanteEntity.nombreInfante( dto.getNombreInfante() );
        fichaInfanteEntity.rutInfante( dto.getRutInfante() );
        fichaInfanteEntity.fechaNacimientoInfante( dto.getFechaNacimientoInfante() );
        fichaInfanteEntity.region( dto.getRegion() );
        fichaInfanteEntity.comuna( dto.getComuna() );
        fichaInfanteEntity.direccion( dto.getDireccion() );
        fichaInfanteEntity.ingresoInfante( dto.getIngresoInfante() );
        fichaInfanteEntity.edadNna( dto.getEdadNna() );
        fichaInfanteEntity.rangoEdadNna( dto.getRangoEdadNna() );
        fichaInfanteEntity.sexoNna( dto.getSexoNna() );
        fichaInfanteEntity.nacionalidadNna( dto.getNacionalidadNna() );
        fichaInfanteEntity.ingresoAfac( dto.getIngresoAfac() );

        return fichaInfanteEntity.build();
    }
}
