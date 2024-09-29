package org.example.chillingdogspage.Controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("/landing")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
public class LandingController {

    //http://localhost:8080/landing
    @GetMapping
    public String landing(){
        return "index";
    }
}
