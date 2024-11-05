package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.DTOs.PerfilDTO;
import org.example.chillingdogspage.DTOs.UsuarioDTO;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Seguridad.JWTGenerator;
import org.example.chillingdogspage.Servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("veterinarios")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Veterinario", description = "API para el manejo de veterinarios")  // Tag para la documentación de la API
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    // GET =============================================================================================================
    //http://localhost:8099/veterinarios
    @GetMapping("")
    @Operation(summary = "Mostrar todos los veterinarios")
    public ResponseEntity<List<Veterinario>> mostrarVeterinarios() {
        List<Veterinario> veterinarios = veterinarioService.findAll();
        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    // Hacer el login de un veterinario con su cedula y contraseña
    //http://localhost:8099/veterinarios/login
    @PostMapping("login")
    @Operation(summary = "Hacer login de un veterinario")
    public ResponseEntity<String> loginVeterinario(@RequestBody UsuarioDTO usuarioDTO) {
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

    //http://localhost:8099/veterinarios/perfil
    @GetMapping("/perfil")
    @Operation(summary = "Mostrar la foto y nombre del veterinario logueado")
    public ResponseEntity<PerfilDTO> mostrarPerfil() {
        // Obtener el usuario autenticado
        Veterinario veterinario = veterinarioService.findByCedula(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (veterinario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        PerfilDTO perfilDTO = PerfilDTO.builder()
                .nombre(veterinario.getNombre())
                .foto(veterinario.getFoto())
                .rol("VETERINARIO")
                .build();
        return ResponseEntity.ok(perfilDTO); // 200 OK
    }

    //http://localhost:8099/veterinarios/{cedula}
    @GetMapping("/{cedula}")
    @Operation(summary = "Obtener los detalles de un veterinario por su cedula")
    public ResponseEntity<Veterinario> obtenerVeterinario(@PathVariable("cedula") String cedula) {
        Veterinario veterinario = veterinarioService.findByCedula(cedula);
        if (veterinario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(veterinario);  // 200 OK
    }

    //http://localhost:8099/veterinarios/nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    @Operation(summary = "Mostrar los veterinarios con nombre similar a 'nombre'")
    public ResponseEntity<List<Veterinario>> mostrarVeterinariosPorNombre(@PathVariable("nombre") String nombre) {
        List<Veterinario> veterinarios = veterinarioService.findBySimilarName(nombre);
        if (veterinarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(veterinarios); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Crear un nuevo veterinario")
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody Veterinario veterinario) {
        Veterinario veterinarioCreado = veterinarioService.createVeterinario(veterinario);
        if (veterinarioCreado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict si ya existe un veterinario con esa cedula
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioCreado); // 201 Created
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de un veterinario")
    public ResponseEntity<Veterinario> actualizarVeterinario(@RequestBody Veterinario veterinario) {
        Veterinario veterinarioActualizado = veterinarioService.updateVeterinario(veterinario);
        if (veterinarioActualizado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 Bad Request si la cedula ya está registrada en otro veterinario
        }
        return ResponseEntity.ok(veterinarioActualizado); // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un veterinario por su ID")
    public ResponseEntity<String> eliminarVeterinario(@PathVariable("id") Long id) {
        if (!veterinarioService.deleteVeterinario(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veterinario no encontrado"); // 404 Not Found si no existe el veterinario
        }
        return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
    }

}