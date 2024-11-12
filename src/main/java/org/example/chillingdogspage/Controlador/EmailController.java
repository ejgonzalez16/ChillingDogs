package org.example.chillingdogspage.Controlador;

import com.resend.services.emails.model.CreateEmailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.Email;
import org.example.chillingdogspage.Servicio.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("email")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Dashboard", description = "API para traer la información del dashboard") // Tag para la documentación de la API
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("")
    @Operation(summary = "Enviar un email")
    public ResponseEntity<String> enviarEmail(@RequestBody Email email) {
        CreateEmailResponse respuesta = emailService.enviarEmail(email);
        return ResponseEntity.ok("EL correo se envió con éxito!!");
    }
}
