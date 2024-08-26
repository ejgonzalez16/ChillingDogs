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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    // Create ===========================================================================================
    @GetMapping("registrar")
    public String registrarCliente(Model model) {
        Cliente cliente = new Cliente("","","","", "");
        model.addAttribute("cliente", cliente);
        return "registrar_cliente";
    }

    @PostMapping("registrar")
    public String registrarCliente(Model model, Cliente cliente) {
        clienteService.registrarCliente(cliente);
        return "redirect:/clientes/buscar";
    }

    // Retrieve ===========================================================================================
    @GetMapping("buscar")
    public String verClienteyMascotas(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes().stream().toList();
        model.addAttribute("clientes", clientes);
        return "buscar_clientes";
    }

    @PostMapping("buscar")
    public String buscarCliente(@RequestParam("cedulaCliente") Integer cedulaCliente) {
        return "redirect:/clientes/buscar/" + cedulaCliente;
    }

    @GetMapping("buscar/{cedula}")
    public String verClienteyMascotas(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "detalles_cliente_cedula";
    }

    // Update ===========================================================================================
    @GetMapping("modificar")
    public String modificarCliente() {
        return "modificar_cliente";
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
        return "modificar_cliente_cedula";
    }

    @PostMapping("modificar/{cedula}")
    public String modificarCliente(Model model, Cliente cliente, @PathVariable("cedula") Integer cedulaAnterior) {
        clienteService.modificarCliente(cliente);
        return "redirect:/clientes/buscar";
    }

    // Delete ===========================================================================================
    @GetMapping("eliminar")
    public String eliminarCliente() {
        return "eliminar_cliente";
    }

    @PostMapping("eliminar")
    public String eliminarCliente(@RequestParam("cedulaCliente") Integer cedulaCliente) {
        return "redirect:/clientes/eliminar/" + cedulaCliente;
    }

    @GetMapping("eliminar/{cedula}")
    public String eliminarCliente(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "eliminar_cliente_cedula";
    }

    @PostMapping("eliminar/{cedula}")
    public String eliminarCliente(Model model, Cliente cliente) {
        clienteService.eliminarCliente(cliente);
        return "redirect:/clientes/buscar";
    }
    
}
