package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Tratamiento;
import org.example.chillingdogspage.DTOs.TratamientoDTO;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Servicio.TratamientoService;
import org.example.chillingdogspage.Servicio.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de un vista (HTML)
@RequestMapping("tratamientos")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Tratamiento", description = "API para el manejo de tratamientos")  // Tag para la documentación de la API
public class TratamientoController {
    @Autowired
    TratamientoService tratamientoService;

    @Autowired
    VeterinarioService veterinarioService;

    // GET =============================================================================================================
    //http://localhost:8099/tratamientos/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de un tratamiento por su ID")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable("id") Long id) {
        Tratamiento tratamiento = tratamientoService.findById(id);
        if (tratamiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(tratamiento);  // 200 OK
    }

    //http://localhost:8099/tratamientos/mascota/{id}
    @GetMapping("mascota/{id}")
    @Operation(summary = "Mostrar los tratamientos de una mascota")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientosMascota(@PathVariable("id") Long id){
        List<Tratamiento> tratamientos = tratamientoService.findAllByMascotaId(id);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tratamientos);
        }
        // Debería ir un método para verificar si el usuario autenticado tiene permiso para acceder a los tratamientos de la mascota
        // Pero zzz
        return ResponseEntity.ok(tratamientos); // 200 OK
    }

    //http://localhost:8099/tratamientos/veterinario
    @GetMapping("veterinario")
    @Operation(summary = "Mostrar los tratamientos del veterinario logueado")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientosVeterinarioLogueado(){
        // Obtener el usuario autenticado
        Veterinario veterinario = veterinarioService.findByCedula(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (veterinario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);   // 403 Forbidden
        }
        List<Tratamiento> tratamientos = tratamientoService.findAllByVeterinarioId(veterinario.getId());
        return ResponseEntity.ok(tratamientos); // 200 OK
    }

    //http://localhost:8099/tratamientos/veterinario/{id}
    @GetMapping("veterinario/{id}")
    @Operation(summary = "Mostrar los tratamientos de un veterinario")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientosVeterinario(@PathVariable("id") Long id){
        List<Tratamiento> tratamientos = tratamientoService.findAllByVeterinarioId(id);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tratamientos);
        }
        return ResponseEntity.ok(tratamientos); // 200 OK
    }

    //http://localhost:8099/tratamientos
    @GetMapping("")
    @Operation(summary = "Mostrar todos los tratamientos")
    public ResponseEntity<List<Tratamiento>> mostrarTratamientos(){
        List<Tratamiento> tratamientos = tratamientoService.findAll();
        return ResponseEntity.ok(tratamientos); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Registrar un nuevo tratamiento para una mascota")
    public ResponseEntity<Tratamiento> registrarTratamiento(@RequestBody TratamientoDTO tratamientoDTO) {
        // Obtener el usuario autenticado
        Veterinario veterinario = veterinarioService.findByCedula(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (veterinario == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);   // 403 Forbidden
        }
        tratamientoDTO.setVeterinarioId(veterinario.getId());
        Tratamiento tratamiento = tratamientoService.registrarTratamiento(tratamientoDTO);
        if (tratamiento == null) {
            // 404 Not Found si no existe la mascota, la droga o el veterinario (o no hay suficiente droga)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamiento);    // 201 Created si se crea correctamente
    }

    /* PUT =============================================================================================================
     @PutMapping("")
     @Operation(summary = "Actualizar los datos de un tratamiento")
     public ResponseEntity<Tratamiento> actualizarTratamiento(@RequestBody TratamientoDTO tratamientoDTO) {
         Tratamiento tratamiento = tratamientoService.updateTratamiento(tratamientoDTO);
         if (tratamiento == null) {
             // 404 Not Found si no existe el tratamiento, la nueva mascota, droga o veterinario (o no hay suficiente de la droga nueva)
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
         return ResponseEntity.ok(tratamiento);  // 200 OK
     }

     DELETE ==========================================================================================================
     @DeleteMapping("/{id}")
     @Operation(summary = "Eliminar un tratamiento por su ID")
     public ResponseEntity<String> eliminarTratamiento(@PathVariable("id") Long id) {
         if (!tratamientoService.deleteTratamiento(id)) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tratamiento no encontrada"); // 404 Not Found si no existe la tratamiento
         }
         return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
     }*/
}