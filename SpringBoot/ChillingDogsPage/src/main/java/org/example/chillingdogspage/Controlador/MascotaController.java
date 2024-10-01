package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.http.ResponseEntity;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("mascotas")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
@Tag(name = "Mascota", description = "API para el manejo de mascotas")  // Tag para la documentación de la API
public class MascotaController {
    @Autowired
    MascotaService mascotaService;
    @Autowired
    ClienteService clienteService;
    String carpeta = "mascotas/";

    // URLs del Cliente =====================================================
    // Retrieve =============================================================

    //http://localhost:8099/mascotas/buscar
    @PostMapping("buscar")
    public String buscarPorNombre(Model model, @RequestParam("nombrePerro") String nombre){
        // Poner el nombre en minúsculas
        nombre = nombre.toLowerCase();
        List<Mascota> mascotas =  mascotaService.searchBySimilarName(nombre).stream().toList();
        if (mascotas.isEmpty()) {
            return ResponseEntity.status(404).body(mascotas);
        }
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    //http://localhost:8099/mascotas
    @GetMapping("all")
    @Operation(summary = "Mostrar todas las mascotas")
    public List<Mascota> mostrarMascotas(){
        return mascotaService.findAll();
    }

    @GetMapping("cliente/{id}")
    @Operation(summary = "Mostrar todas las mascotas")
    public List<Mascota> findMascotasByClienteId(@PathVariable("id") String id){
        return mascotaService.findByClienteId(id);;
    }

    //http://localhost:8099/mascotas/nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    @Operation(summary = "Mostrar las mascotas con nombre similar a 'nombre'")
    public ResponseEntity<List<Mascota>> mostrarMascotasPorNombre(@PathVariable("nombre") String nombre){
        List<Mascota> mascotas = mascotaService.findBySimilarName(nombre);
        return ResponseEntity.ok(mascotas); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("/cliente/{cedula}")
    @Operation(summary = "Registrar una nueva mascota para un cliente")
    public ResponseEntity<Mascota> registrarMascota(@PathVariable("cedula") String cedula, @RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.createMascota(mascota, cedula);
        return ResponseEntity.status(201).body(nuevaMascota);   // 201 Created
    }

    @PostMapping("add")
    @Operation(summary = "Registrar una nueva mascota para un cliente")
    public void registrarMascota(@RequestBody Mascota mascota) {
        mascotaService.registrarMascota(mascota);
    }

    // PUT =============================================================================================================
    @PutMapping("update/{id}")
    @Operation(summary = "Actualizar los datos de una mascota")
    public void actualizarMascota(@RequestBody Mascota mascotaActualizada) {
        mascotaService.updateMascota(mascotaActualizada);
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Eliminar una mascota por su ID")
    public void eliminarMascota(@PathVariable("id") Long id) {
        mascotaService.deleteMascota(id);
    }
}
