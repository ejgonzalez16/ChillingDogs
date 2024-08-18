package org.example.chillingdogspage.Controlador;

import org.springframework.ui.Model;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteServicio;
    @GetMapping("buscar")
    public String verClienteyMascotas(Model model){
        String cedulaCliente = "";
        model.addAttribute("cedulaCliente", cedulaCliente);
        return "verClienteyMascotas";
    }

    @PostMapping("/buscar/{cedula}")
    public String verClienteyMascotas(@PathVariable("cedula") String cedula, Model model){
        model.addAttribute("cliente", clienteServicio.buscarCliente(cedula));
        return "verClienteyMascotasCedula";
    }
}
