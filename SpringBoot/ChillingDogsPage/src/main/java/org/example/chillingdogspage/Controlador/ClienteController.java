package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("clientes")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
@Tag(name = "Cliente", description = "API para el manejo de clientes")  // Tag para la documentación de la API
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    // GET =============================================================================================================
    //http://localhost:8099/clientes
    @GetMapping("")
    @Operation(summary = "Mostrar todos los clientes")
    public ResponseEntity<List<Cliente>> mostrarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes); // 200 OK
    }

    //http://localhost:8099/clientes/{cedula}
    @GetMapping("/{cedula}")
    @Operation(summary = "Obtener los detalles de un cliente por su cedula")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.findByCedula(cedula);
        if (cliente == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(cliente);  // 200 OK
    }

    //http://localhost:8099/clientes/nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    @Operation(summary = "Mostrar los clientes con nombre similar a 'nombre'")
    public ResponseEntity<List<Cliente>> mostrarClientesPorNombre(@PathVariable("nombre") String nombre) {
        List<Cliente> clientes = clienteService.findBySimilarName(nombre);
        if (clientes.isEmpty()) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(clientes); // 200 OK
    }

    // POST ============================================================================================================
    @PostMapping("")
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente clienteCreado = clienteService.createCliente(cliente);
        return ResponseEntity.status(201).body(clienteCreado); // 201 Created
    }

    // PUT =============================================================================================================
    @PutMapping("")
    @Operation(summary = "Actualizar los datos de un cliente")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.updateCliente(cliente);
        if (clienteActualizado == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(clienteActualizado); // 200 OK
    }

    // DELETE ==========================================================================================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por su ID")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") Long id) {
        if (!clienteService.deleteCliente(id)) {
            return ResponseEntity.status(404).body("Cliente no encontrado"); // 404 Not Found si no existe el cliente
        }
        return ResponseEntity.ok("Cliente eliminado exitosamente"); // 200 OK si se elimina correctamente
    }

}