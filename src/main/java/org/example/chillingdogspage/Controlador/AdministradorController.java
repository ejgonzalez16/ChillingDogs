package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.DTOs.PerfilDTO;
import org.example.chillingdogspage.DTOs.UsuarioDTO;
import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Seguridad.JWTGenerator;
import org.example.chillingdogspage.Servicio.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("admin")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Administrador", description = "API para el manejo de admin")  // Tag para la documentación de la API
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    // GET =============================================================================================================
    // Hacer el login de un administrador con su cedula y contraseña
    //http://localhost:8099/admin/login
    @PostMapping("/login")
    @Operation(summary = "Hacer login de un administrador")
    public ResponseEntity<String> loginAdministrador(@RequestBody UsuarioDTO usuarioDTO) {
        // Authentication guarda las credenciales del usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(), usuarioDTO.getPassword())
        );

        // Guardar la autenticación en el contexto de seguridad
        // A través de SecurityContextHolder se puede obtener el usuario autenticado
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        // El Login NO tiene nada que ver con devolver al Usuario
        // Solo me interesa saber si las credenciales son correctas o no
        return ResponseEntity.ok(token); // 200 OK
    }

    //http://localhost:8099/admin/perfil
    @GetMapping("/perfil")
    @Operation(summary = "Mostrar la foto y nombre del administrador logueado")
    public ResponseEntity<PerfilDTO> mostrarPerfil() {
        // Obtener el usuario autenticado
        Administrador administrador = administradorService.findByCedula(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (administrador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        PerfilDTO perfilDTO = PerfilDTO.builder()
                .nombre(administrador.getNombre())
                .foto(administrador.getFoto())
                .build();
        return ResponseEntity.ok(perfilDTO); // 200 OK
    }
}