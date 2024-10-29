package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.http.ResponseEntity;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("mascotas")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Mascota", description = "API para el manejo de mascotas")  // Tag para la documentación de la API
public class MascotaController {
    @Autowired
    MascotaService mascotaService;

    // GET =============================================================================================================
    //http://localhost:8099/mascotas/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de una mascota por su ID")
    public ResponseEntity<Mascota> obtenerMascota(@PathVariable("id") Long id) {
        Mascota mascota = mascotaService.findById(id);
        return ResponseEntity.ok(mascota);  // 200 OK
    }

    //http://localhost:8099/mascotas/cliente/{cedula}
    @GetMapping("cliente/{cedula}")
    @Operation(summary = "Mostrar las mascotas de un cliente")
    public ResponseEntity<List<Mascota>> mostrarMascotasCliente(@PathVariable("cedula") String cedula){
        List<Mascota> mascotas = mascotaService.findAllByClienteCedula(cedula);
        if (mascotas.isEmpty()) {
            return ResponseEntity.status(404).body(mascotas);
        }
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    //http://localhost:8099/mascotas
    @GetMapping("")
    @Operation(summary = "Mostrar todas las mascotas")
    public ResponseEntity<List<Mascota>> mostrarMascotas(){
        List<Mascota> mascotas = mascotaService.findAll();
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    //http://localhost:8099/mascotas/nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    @Operation(summary = "Mostrar las mascotas con nombre similar a 'nombre'")
    public ResponseEntity<List<Mascota>> mostrarMascotasPorNombre(@PathVariable("nombre") String nombre){
        List<Mascota> mascotas = mascotaService.findBySimilarName(nombre);
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Registrar una nueva mascota para un cliente")
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.registrarMascota(mascota);
        return ResponseEntity.status(201).body(nuevaMascota);   // 201 Created
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de una mascota")
    public ResponseEntity<Mascota> actualizarMascota(@RequestBody Mascota mascotaActualizada) {
        Mascota mascota = mascotaService.updateMascota(mascotaActualizada);
        return ResponseEntity.ok(mascota);  // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mascota por su ID")
    public ResponseEntity<String> eliminarMascota(@PathVariable("id") Long id) {
        if (!mascotaService.deleteMascota(id)) {
            return ResponseEntity.status(404).body("Mascota no encontrada"); // 404 Not Found si no existe la mascota
        }
        return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
    }
}