package cl.somosafac.afacbackend.service;

import com.AFAC_BackEnd.AFAC.DTO.NotificacionesDTO;
import java.util.List;

public interface NotificacionesService {

    List<NotificacionesDTO> obtenerTodasLasNotificaciones();

    NotificacionesDTO obtenerNotificacionPorId(Long id);

    NotificacionesDTO crearNotificacion(NotificacionesDTO notificacion);

    NotificacionesDTO actualizarNotificacion(Long id, NotificacionesDTO notificacion);

}
