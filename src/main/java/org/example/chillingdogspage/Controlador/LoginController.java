package org.example.chillingdogspage.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("/login")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
public class LoginController {

    //http://localhost:8099/login/
    @GetMapping
    public String login(){
        return "login";
    }
}
