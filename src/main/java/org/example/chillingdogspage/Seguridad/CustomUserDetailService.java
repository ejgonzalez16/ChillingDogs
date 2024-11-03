package org.example.chillingdogspage.Seguridad;

import org.example.chillingdogspage.Entidad.Rol;
import org.example.chillingdogspage.Entidad.Usuario;
import org.example.chillingdogspage.Repositorio.RolRepository;
import org.example.chillingdogspage.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Mapear datos de Usuario -> UserDetails
     * Retorna un UserDetails, que es la entidad básica en springboot que únicamente tiene
     * username, password y authorities
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el Usuario en la base de datos
        Usuario usuarioDB = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario no encontrado")
        );

        // Se mapean los datos desde el Usuario a UserDetail
        // User es una implementación de UserDetails
        return User.builder()
                .username(usuarioDB.getUsername())
                .password(passwordEncoder.encode(usuarioDB.getPassword()))
                .authorities(mapRolesToAuthorities(usuarioDB.getRoles()))
                .build();
    }

    // Mapear Rol -> GrantedAuthority (Interfaz que representa un rol)
    private List<GrantedAuthority> mapRolesToAuthorities(List<Rol> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }

    // Guardar un usuario en la base de datos dado su cédula, contraseña y rol
    public Usuario guardarUsuario(String cedula, String contrasena, String rol) {
        // Buscar si el usuario ya existe
        Usuario usuarioDB = usuarioRepository.findByUsername(cedula).orElse(null);
        // Buscar el rol en la base de datos, si no existe, lanzar una excepción
        Rol rolDB = rolRepository.findByNombre(rol).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        // Si el usuario no existe, crearlo asignándole el rol
        if (usuarioDB == null) {
            usuarioDB = Usuario.builder()
                    .username(cedula)
                    .password(passwordEncoder.encode(contrasena))
                    .roles(List.of(rolDB))
                    .build();
        } else {
            usuarioDB.addRol(rolDB);
        }

        return usuarioRepository.save(usuarioDB);
    }

}
