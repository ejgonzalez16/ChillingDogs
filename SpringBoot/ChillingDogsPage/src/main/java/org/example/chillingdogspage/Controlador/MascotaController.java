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
    //http://localhost:8099/mascotas/mis-mascotas/{cedula}
    @GetMapping("mis-mascotas/{cedula}")
    public String misMascotas(Model model, @PathVariable String cedula){
        Cliente cliente = clienteService.buscarCliente(cedula);
        if(cliente == null){
            throw new ClientNotFoundException(cedula);
        }
        model.addAttribute("cliente", cliente);
        return carpeta + "mis_mascotas";
    }

    //http://localhost:8099/mascotas/
    @GetMapping("{id}")
    public String miMascota(Model model, @PathVariable int id){
        model.addAttribute("mascota", mascotaService.findById(id));
        return carpeta + "mascota";
    }

    // URLs del Veterinario =====================================================
    // Create =============================================================
    @GetMapping("registrar")
    public String registrarMascota(Model model) {
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clienteService.obtenerClientes());
        return carpeta + "registrar_mascota";
    }

    @PostMapping("registrar")
    public String registrarMascota(Mascota mascota) {
        mascota.setCliente(clienteService.buscarCliente(mascota.getCliente().getCedula()));
        mascotaService.registrarMascota(mascota);
        return "redirect:/mascotas/buscar";
    }

    // Retrieve =============================================================
    //http://localhost:8099/mascotas/buscar
    @GetMapping("buscar")
    public String buscar(Model model){
        actualizarMascotasCliente(model);
        return carpeta + "buscar_mascotas";
    }

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
    @PostMapping("/cliente/{cedula}")
    @Operation(summary = "Registrar una nueva mascota para un cliente")
    public ResponseEntity<Mascota> registrarMascota(@PathVariable("cedula") String cedula, @RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.createMascota(mascota, cedula);
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
    public ResponseEntity<Void> eliminarMascota(@PathVariable("id") Long id) {
        mascotaService.deleteMascota(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
