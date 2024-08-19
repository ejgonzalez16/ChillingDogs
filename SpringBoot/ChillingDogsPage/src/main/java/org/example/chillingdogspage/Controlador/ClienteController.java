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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
  
    @GetMapping("buscar")
    public String verClienteyMascotas() {
        return "verClienteyMascotas";
    }

    @PostMapping("buscar")
    public String buscarCliente(@RequestParam("cedulaCliente") Integer cedulaCliente) {
        return "redirect:/clientes/buscar/" + cedulaCliente;
    }

    @GetMapping("buscar/{cedula}")
    public String verClienteyMascotas(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "verClienteyMascotasCedula";
    }

    @GetMapping("registrar")
    public String registrarCliente(Model model) {
        Cliente cliente = new Cliente("","","","", "",null);
        model.addAttribute("cliente", cliente);
        return "registrarCliente";
    }

    @PostMapping("registrar")
    public String registrarCliente(Model model, Cliente cliente) {
        clienteService.registrarCliente(cliente);
        return "redirect:/clientes/buscar";
    }

    @GetMapping("modificar")
    public String modificarCliente() {
        return "modificarCliente";
    }

    @PostMapping("modificar")
    public String modificarCliente(@RequestParam("cedulaCliente") Integer cedulaCliente) {
        return "redirect:/clientes/modificar/" + cedulaCliente;
    }

    @GetMapping("modificar/{cedula}")
    public String modificarCliente(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        model.addAttribute("cedulaAnterior", cedula);
        return "modificarClienteCedula";
    }

    @PostMapping("modificar/{cedula}")
    public String modificarCliente(Model model, Cliente cliente, @PathVariable("cedula") Integer cedulaAnterior) {
        clienteService.modificarCliente(cedulaAnterior, cliente);
        return "redirect:/clientes/buscar";
    }

    @GetMapping("eliminar")
    public String eliminarCliente() {
        return "eliminarCliente";
    }

    @PostMapping("eliminar")
    public String eliminarCliente(@RequestParam("cedulaCliente") Integer cedulaCliente) {
        return "redirect:/clientes/eliminar/" + cedulaCliente;
    }

    @GetMapping("eliminar/{cedula}")
    public String eliminarCliente(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "eliminarClienteCedula";
    }

    @PostMapping("eliminar/{cedula}")
    public String eliminarCliente(Model model, Cliente cliente) {
        clienteService.eliminarCliente(cliente);
        return "redirect:/clientes/buscar";
    }
    
}
