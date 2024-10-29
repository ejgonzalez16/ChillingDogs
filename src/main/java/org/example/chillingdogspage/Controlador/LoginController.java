package org.example.chillingdogspage.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
public class LoginController {

    //http://localhost:8099/login/
    @GetMapping
    public String login(){
        return "login";
    }
}
