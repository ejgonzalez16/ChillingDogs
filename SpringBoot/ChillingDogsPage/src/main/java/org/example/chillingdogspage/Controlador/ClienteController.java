package org.example.chillingdogspage.Controlador;

import org.springframework.ui.Model;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ClienteController {
    @Autowired
    MascotaService mascotaService;

    //http://localhost:8080/mascotas
    @GetMapping("mis-mascotas")
    public String misMascotas(Model model){
        model.addAttribute("mascotas", mascotaService.searchAll());
        return "mis_mascotas";
    }

    //http://localhost:8080/mascotas/{id}
    @GetMapping("mi-mascota")
    public String miMascota(Model model){
        model.addAttribute("mascota", mascotaService.findById(1));
        return "mi_mascota";
    }
}
