package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Servicio.DrogaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de un vista (HTML)
@RequestMapping("drogas")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Droga", description = "API para el manejo de drogas")  // Tag para la documentación de la API
public class DrogaController {
    @Autowired
    DrogaService drogaService;

    // GET =============================================================================================================
    //http://localhost:8099/drogas/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de una droga por su ID")
    public ResponseEntity<Droga> obtenerDroga(@PathVariable("id") Long id) {
        Droga droga = drogaService.findById(id);
        if (droga == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(droga);  // 200 OK
    }

    //http://localhost:8099/drogas
    @GetMapping("")
    @Operation(summary = "Mostrar todas las drogas")
    public ResponseEntity<List<Droga>> mostrarDrogas(){
        List<Droga> drogas = drogaService.findAll();
        return ResponseEntity.ok(drogas); // 200 OK
    }

    //http://localhost:8099/drogas/disponibles
    @GetMapping("/disponibles")
    @Operation(summary = "Mostrar todas las drogas disponibles")
    public ResponseEntity<List<Droga>> mostrarDrogasDisponibles() {
        List<Droga> drogas = drogaService.findDisponibles();
        return ResponseEntity.ok(drogas); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Registrar una nueva droga")
    public ResponseEntity<Droga> registrarDroga(@RequestBody Droga droga) {
        Droga droga1 = drogaService.registrarDroga(droga);
        if (droga1 == null) {
            // 404 Not Found si no existe la mascota, la droga o el veterinario (o no hay suficiente droga)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(droga1);    // 201 Created si se crea correctamente
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de una droga")
    public ResponseEntity<Droga> actualizarDroga(@RequestBody Droga droga) {
        Droga droga1 = drogaService.registrarDroga(droga);
        if (droga1 == null) {
            // 404 Not Found si no existe el droga, la nueva mascota, droga o veterinario (o no hay suficiente de la droga nueva)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(droga1);  // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una droga por su ID")
    public ResponseEntity<String> eliminarDroga(@PathVariable("id") Long id) {
        if (!drogaService.deleteDroga(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Droga no encontrada"); // 404 Not Found si no existe la droga
        }
        return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
    }
}
