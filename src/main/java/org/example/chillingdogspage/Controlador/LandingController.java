package org.example.chillingdogspage.Controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("/landing")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
public class LandingController {

    //http://localhost:8080/landing
    @GetMapping
    public String landing(){
        return "index";
    }
}
