package cl.somosafac.afacbackend.service;

import cl.somosafac.afacbackend.DTO.UsuarioDTO;
import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.mapper.UsuarioMapper;
import cl.somosafac.afacbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Método nuevo para crear usuario
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        // Verificar si el correo ya existe
        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Convertir DTO a Entity
        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioDTO);

        // Configurar valores por defecto
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setVerificado(false);

        // Encriptar la contraseña
        usuario.setContrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()));

        // Asegurar que roles no sea null
        if (usuario.getRoles() == null) {
            usuario.setRoles(new HashSet<>());
        }

        // Agregar el rol principal como tipo de usuario si no está presente
        usuario.getRoles().add(usuario.getTipoUsuario());

        // Guardar el usuario
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir Entity a DTO y retornar
        return usuarioMapper.toDTO(usuarioGuardado);
    }

    // Métodos existentes...
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDTOList(usuarios);
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow();
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id).orElseThrow();
        usuarioMapper.updateEntityFromDTO(usuarioDTO, usuarioExistente);
        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDTO(usuarioActualizado);
    }
}