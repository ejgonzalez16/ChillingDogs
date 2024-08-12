package org.example.chillingdogspage.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    //http://localhost:8080/cliente
    @GetMapping
    public String cliente(){
        return "sesion_cliente";
    }
}
