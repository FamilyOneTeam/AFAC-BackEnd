package cl.somosafac.afacbackend.repository;

import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreo(String correo);
    Optional<UsuarioEntity> findByResetToken(String token);
    List<UsuarioEntity> findByTipoUsuario(Role tipoUsuario);
}