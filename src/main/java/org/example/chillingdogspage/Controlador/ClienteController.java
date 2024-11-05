package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.DTOs.PerfilDTO;
import org.example.chillingdogspage.DTOs.UsuarioDTO;
import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Seguridad.JWTGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("clientes")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Cliente", description = "API para el manejo de clientes")  // Tag para la documentación de la API
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    // GET =============================================================================================================
    //http://localhost:8099/clientes
    @GetMapping("")
    @Operation(summary = "Mostrar todos los clientes")
    public ResponseEntity<List<Cliente>> mostrarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes); // 200 OK
    }

    // Hacer el login de un cliente con su cedula y contraseña
    //http://localhost:8099/clientes/login
    @PostMapping("/login")
    @Operation(summary = "Hacer login de un cliente")
    public ResponseEntity<String> loginCliente(@RequestBody UsuarioDTO usuarioDTO) {
        // Authentication guarda las credenciales del usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(), "")
        );

        // Guardar la autenticación en el contexto de seguridad
        // A través de SecurityContextHolder se puede obtener el usuario autenticado
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        // El Login NO tiene nada que ver con devolver al Usuario
        // Solo me interesa saber si las credenciales son correctas o no
        return ResponseEntity.ok(token); // 200 OK
    }

    //http://localhost:8099/clientes/perfil
    @GetMapping("/perfil")
    @Operation(summary = "Mostrar la foto y nombre del cliente logueado")
    public ResponseEntity<PerfilDTO> mostrarPerfil() {
        // Obtener el usuario autenticado
        Cliente cliente = clienteService.findByCedula(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        PerfilDTO perfilDTO = PerfilDTO.builder()
                .nombre(cliente.getNombre())
                .foto(cliente.getFoto())
                .rol("CLIENTE")
                .build();
        return ResponseEntity.ok(perfilDTO); // 200 OK
    }

    //http://localhost:8099/clientes/{cedula}
    @GetMapping("/{cedula}")
    @Operation(summary = "Obtener los detalles de un cliente por su cedula")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.findByCedula(cedula);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(cliente);  // 200 OK
    }

    //http://localhost:8099/clientes/nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    @Operation(summary = "Mostrar los clientes con nombre similar a 'nombre'")
    public ResponseEntity<List<Cliente>> mostrarClientesPorNombre(@PathVariable("nombre") String nombre) {
        List<Cliente> clientes = clienteService.findBySimilarName(nombre);
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(clientes); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.createCliente(cliente);
        if (clienteCreado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict si ya existe un cliente con esa cédula
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado); // 201 Created
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de un cliente")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.updateCliente(cliente);
        if (clienteActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(clienteActualizado); // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por su ID")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") Long id) {
        if (!clienteService.deleteCliente(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado"); // 404 Not Found si no existe el cliente
        }
        return ResponseEntity.ok("Cliente eliminado exitosamente"); // 200 OK si se elimina correctamente
    }

}