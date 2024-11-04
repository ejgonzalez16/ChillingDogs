package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        String cedulaCliente = mascota.getCliente().getCedula();
        // Revisar si el usuario autenticado tiene permiso para acceder a los detalles de la mascota
        if (!isAccessAllowedForCliente(cedulaCliente)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden
        }
        return ResponseEntity.ok(mascota);  // 200 OK
    }

    //http://localhost:8099/mascotas/cliente/{cedula}
    @GetMapping("cliente/{cedula}")
    @Operation(summary = "Mostrar las mascotas de un cliente")
    public ResponseEntity<List<Mascota>> mostrarMascotasCliente(@PathVariable("cedula") String cedula){
        // Revisar si el usuario autenticado tiene permiso para acceder a las mascotas del cliente
        if (!isAccessAllowedForCliente(cedula)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden
        }

        List<Mascota> mascotas = mascotaService.findAllByClienteCedula(cedula);
        if (mascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mascotas);
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
        if (nuevaMascota == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found si no existe el cliente
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);   // 201 Created
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de una mascota")
    public ResponseEntity<Mascota> actualizarMascota(@RequestBody Mascota mascotaActualizada) {
        Mascota mascota = mascotaService.updateMascota(mascotaActualizada);
        if (mascota == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(mascota);  // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mascota por su ID")
    public ResponseEntity<String> eliminarMascota(@PathVariable("id") Long id) {
        if (!mascotaService.deleteMascota(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada"); // 404 Not Found si no existe la mascota
        }
        return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
    }

    private boolean isAccessAllowedForCliente(String cedulaConsultada) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraer roles en un Set para facilitar la comparación
        Set<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        // Extraer nombre de usuario autenticado (cédula)
        String cedulaClienteLogueado = authentication.getName();

        // Si el usuario es únicamente "CLIENTE" y la cédula no coincide retorna false
        // De lo contrario, retorna true
        return !roles.equals(Set.of("CLIENTE")) || cedulaConsultada.equals(cedulaClienteLogueado);
    }
}