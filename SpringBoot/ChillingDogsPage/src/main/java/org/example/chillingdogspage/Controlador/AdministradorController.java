package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Servicio.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
@Tag(name = "Administrador", description = "API para el manejo de admin")  // Tag para la documentación de la API
public class AdministradorController {
    @Autowired
    AdministradorService administradorService;

    // GET =============================================================================================================
    // Hacer el login de un administrador con su cedula y contraseña
    //http://localhost:8099/admin/login
    @PostMapping("/login")
    @Operation(summary = "Hacer login de un administrador")
    public ResponseEntity<Administrador> loginAdministrador(@RequestBody Administrador administrador) {
        Administrador administradorLogueado = administradorService.findByCedulaAndContrasena(administrador.getCedula(), administrador.getContrasena());
        if (administradorLogueado == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(administradorLogueado); // 200 OK
    }

}