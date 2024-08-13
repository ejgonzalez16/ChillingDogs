package org.example.chillingdogspage.Controlador;

import org.springframework.ui.Model;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class MascotaController {
    @Autowired
    MascotaService mascotaService;

    //http://localhost:8099/mascotas
    @GetMapping("mascotas")
    public String misMascotas(Model model){
        model.addAttribute("mascotas", mascotaService.searchAll());
        return "mascotas";
    }

    //http://localhost:8099/mascotas/
    @GetMapping("mascotas/{id}")
    public String miMascota(Model model, @PathVariable int id){
        model.addAttribute("mascota", mascotaService.findById(id));
        return "mascota";
    }
}
