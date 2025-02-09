package cl.somosafac.afacbackend.mapper;

import cl.somosafac.afacbackend.DTO.FichaFamiliasDTO;
import cl.somosafac.afacbackend.entity.FichaFamiliasEntity;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class FichaFamiliasMapperImpl implements FichaFamiliasMapper {

    @Override
    public FichaFamiliasDTO toDTO(FichaFamiliasEntity entity) {
        if ( entity == null ) {
            return null;
        }

        FichaFamiliasDTO fichaFamiliasDTO = new FichaFamiliasDTO();

        fichaFamiliasDTO.setUsuarioId( entityUsuarioId( entity ) );
        fichaFamiliasDTO.setId( entity.getId() );
        fichaFamiliasDTO.setNombreFaUno( entity.getNombreFaUno() );
        fichaFamiliasDTO.setNombreFaDos( entity.getNombreFaDos() );
        fichaFamiliasDTO.setRutFaUno( entity.getRutFaUno() );
        fichaFamiliasDTO.setRutFaDos( entity.getRutFaDos() );
        fichaFamiliasDTO.setFechaNacimientoFaUno( entity.getFechaNacimientoFaUno() );
        fichaFamiliasDTO.setFechaNacimientoFaDos( entity.getFechaNacimientoFaDos() );
        fichaFamiliasDTO.setEstadoCivil( entity.getEstadoCivil() );
        fichaFamiliasDTO.setTelefono( entity.getTelefono() );
        fichaFamiliasDTO.setEmail( entity.getEmail() );
        fichaFamiliasDTO.setRegion( entity.getRegion() );
        fichaFamiliasDTO.setComuna( entity.getComuna() );
        fichaFamiliasDTO.setDireccion( entity.getDireccion() );
        fichaFamiliasDTO.setProgramaFundacionActual( entity.getProgramaFundacionActual() );
        fichaFamiliasDTO.setProgramaFundacionAnterior( entity.getProgramaFundacionAnterior() );
        fichaFamiliasDTO.setIngresoFa( entity.getIngresoFa() );
        fichaFamiliasDTO.setEstadoAcogimiento( entity.getEstadoAcogimiento() );
        fichaFamiliasDTO.setFechaInicioAcogimiento( entity.getFechaInicioAcogimiento() );

        return fichaFamiliasDTO;
    }

    @Override
    public FichaFamiliasEntity toEntity(FichaFamiliasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FichaFamiliasEntity.FichaFamiliasEntityBuilder fichaFamiliasEntity = FichaFamiliasEntity.builder();

        fichaFamiliasEntity.usuario( mapId( dto.getUsuarioId() ) );
        fichaFamiliasEntity.id( dto.getId() );
        fichaFamiliasEntity.nombreFaUno( dto.getNombreFaUno() );
        fichaFamiliasEntity.nombreFaDos( dto.getNombreFaDos() );
        fichaFamiliasEntity.rutFaUno( dto.getRutFaUno() );
        fichaFamiliasEntity.rutFaDos( dto.getRutFaDos() );
        fichaFamiliasEntity.fechaNacimientoFaUno( dto.getFechaNacimientoFaUno() );
        fichaFamiliasEntity.fechaNacimientoFaDos( dto.getFechaNacimientoFaDos() );
        fichaFamiliasEntity.estadoCivil( dto.getEstadoCivil() );
        fichaFamiliasEntity.telefono( dto.getTelefono() );
        fichaFamiliasEntity.email( dto.getEmail() );
        fichaFamiliasEntity.region( dto.getRegion() );
        fichaFamiliasEntity.comuna( dto.getComuna() );
        fichaFamiliasEntity.direccion( dto.getDireccion() );
        fichaFamiliasEntity.programaFundacionActual( dto.getProgramaFundacionActual() );
        fichaFamiliasEntity.programaFundacionAnterior( dto.getProgramaFundacionAnterior() );
        fichaFamiliasEntity.ingresoFa( dto.getIngresoFa() );
        fichaFamiliasEntity.estadoAcogimiento( dto.getEstadoAcogimiento() );
        fichaFamiliasEntity.fechaInicioAcogimiento( dto.getFechaInicioAcogimiento() );

        return fichaFamiliasEntity.build();
    }

    @Override
    public List<FichaFamiliasDTO> toDTOList(List<FichaFamiliasEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FichaFamiliasDTO> list = new ArrayList<FichaFamiliasDTO>( entities.size() );
        for ( FichaFamiliasEntity fichaFamiliasEntity : entities ) {
            list.add( toDTO( fichaFamiliasEntity ) );
        }

        return list;
    }

    private Long entityUsuarioId(FichaFamiliasEntity fichaFamiliasEntity) {
        UsuarioEntity usuario = fichaFamiliasEntity.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
