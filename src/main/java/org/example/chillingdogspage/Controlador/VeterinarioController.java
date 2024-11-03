package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/login")
    @Operation(summary = "Hacer login de un veterinario")
    public ResponseEntity<Veterinario> loginVeterinario(@RequestBody Veterinario veterinario) {
        Veterinario veterinarioLogueado = veterinarioService.findByCedulaAndContrasena(veterinario.getCedula(), veterinario.getContrasena());
        if (veterinarioLogueado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(veterinarioLogueado); // 200 OK
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
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