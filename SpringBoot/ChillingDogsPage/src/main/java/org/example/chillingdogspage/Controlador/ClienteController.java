package org.example.chillingdogspage.Controlador;

import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.ui.Model;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200/")
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    MascotaService mascotaService;

    // Create ===========================================================================================
    //http://localhost:8099/clientes/registrar


    @GetMapping("all")
    public List<Cliente> getClientes(){
        return this.clienteService.obtenerClientes();
    }

    //http://localhost:8099/mascotas/{id}
    @GetMapping("{id}")
    public Cliente getCliente(@PathVariable int id){
        return this.clienteService.getCliente(id);
    }

    @PostMapping("add")
    public Cliente addCliente(@RequestBody Cliente cliente){
        this.clienteService.registrarCliente(cliente);
        return cliente;
    }

    @PutMapping("update/{id}")
    public Cliente updateClientes(@RequestBody Cliente cliente){
        this.clienteService.registrarCliente(cliente);
        return cliente;
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable int id){
        this.clienteService.deleteClienteById(id);
        return "hecho";
    }


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

    //http://localhost:8099/clientes/buscar
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

    //http://localhost:8099/clientes/buscar/{cedula}
    @GetMapping("buscar/{cedula}")
    public String verClienteyMascotas(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "detalles_cliente_cedula";
    }

    // Update ===========================================================================================
    //http://localhost:8099/clientes/modificar/{cedula}
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
    // http://localhost:8099/clientes/eliminar/{cedula}
    @GetMapping("eliminar/{cedula}")
    public String eliminarCliente(@PathVariable("cedula") Integer cedula, Model model) {
        Cliente cliente = clienteService.buscarCliente(cedula.toString());
        model.addAttribute("cliente", cliente);
        return "eliminar_cliente_cedula";
    }

    @PostMapping("eliminar/{cedula}")
    public String eliminarCliente(Model model, Cliente cliente) {
        clienteService.eliminarCliente(cliente.getCedula());
        return "redirect:/clientes/buscar";
    }
    
}
