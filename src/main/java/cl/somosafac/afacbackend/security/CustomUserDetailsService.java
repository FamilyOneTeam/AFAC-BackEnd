package cl.somosafac.afacbackend.security;


import cl.somosafac.afacbackend.entity.UsuarioEntity;
import cl.somosafac.afacbackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.name()))
        );

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasenaHash())
                .authorities(authorities)
                .build();
    }
}