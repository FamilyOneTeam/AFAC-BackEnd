package cl.somosafac.afacbackend.service;

import com.AFAC_BackEnd.AFAC.DTO.UsuarioDTO;
import com.AFAC_BackEnd.AFAC.entity.UsuarioEntity;
import com.AFAC_BackEnd.AFAC.mapper.UsuarioMapper;
import com.AFAC_BackEnd.AFAC.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper; // Asegúrate de inyectar el mapper

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDTOList(usuarios); // Usa el mapper aquí
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow();
        return usuarioMapper.toDTO(usuario); // Convertir Entity a DTO
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id).orElseThrow();
        usuarioMapper.updateEntityFromDTO(usuarioDTO, usuarioExistente); // Actualiza la entidad con el DTO
        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDTO(usuarioActualizado);
    }


}