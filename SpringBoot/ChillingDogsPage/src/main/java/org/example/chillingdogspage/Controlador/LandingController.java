package org.example.chillingdogspage.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landing")
public class LandingController {

    //http://localhost:8080/landing
    @GetMapping
    public String landing(){
        return "index";
    }
}
