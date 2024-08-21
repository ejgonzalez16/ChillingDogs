package org.example.chillingdogspage.Controlador;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Entidad.MascotaCliente;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.ui.Model;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MascotaController {
    @Autowired
    MascotaService mascotaService;
    @Autowired
    ClienteService clienteService;

    // URLs del Cliente =====================================================
    // Retrieve =============================================================
    //http://localhost:8099/mis-mascotas/{cedula}
    @GetMapping("mis-mascotas/{cedula}")
    public String misMascotas(Model model, @PathVariable String cedula){
        Cliente cliente = clienteService.buscarCliente(cedula);
        if(cliente == null){
            throw new ClientNotFoundException(cedula);
        }
        model.addAttribute("cliente", cliente);
        return "mis_mascotas";
    }

    //http://localhost:8099/mascotas/
    @GetMapping("mascotas/{id}")
    public String miMascota(Model model, @PathVariable int id){
        model.addAttribute("mascota", mascotaService.findById(id));
        return "mascota";
    }

    // URLs del Veterinario =====================================================
    // Create =============================================================
    @GetMapping("mascotas/registrar")
    public String registrarMascota(Model model) {
        Mascota mascota = new Mascota(-1,"","",-1,-1.0f, "", "", "");
        model.addAttribute("mascota", mascota);
        return "registrar_mascota";
    }

    @PostMapping("mascotas/registrar")
    public String registrarMascota(Mascota mascota) {
        mascotaService.registrarMascota(mascota);
        return "redirect:/mascotas";
    }

    // Retrieve =============================================================
    //http://localhost:8099/mascotas
    @GetMapping("mascotas")
    public String getMascotas(Model model){
        actualizarMascotasCliente(model);
        return "mascotas";
    }

    //http://localhost:8099/mascotas/buscar-por-nombre
    @GetMapping("mascotas/buscar-por-nombre")
    public String buscarPorNombre(Model model){
        actualizarMascotasCliente(model);
        return "buscar_mascotas";
    }

    //http://localhost:8099/mascotas/detalles-completos/{id}
    @GetMapping("mascotas/detalles-completos/{id}")
    public String detallesCompletos(Model model, @PathVariable int id){
        Mascota mascota =  mascotaService.findById(id);
        Cliente cliente = clienteService.buscarClientePorMascota(mascota.getId());
        MascotaCliente mascotaCliente = new MascotaCliente(cliente.getCedula(), cliente.getNombre(), mascota);
        model.addAttribute("mascota", mascotaCliente);
        model.addAttribute("cliente", cliente);
        return "detalles_mascota";
    }

    // Update =============================================================
    //http://localhost:8099/mascotas/modificar
    @GetMapping("mascotas/modificar")
    public String modificarMascotas(Model model){
        actualizarMascotasCliente(model);
        return "modificar_mascotas";
    }

    // Delete =============================================================
    //http://localhost:8099/mascotas/eliminar
    @GetMapping("mascotas/eliminar")
    public String eliminarMascotas(Model model){
        actualizarMascotasCliente(model);
        return "eliminar_mascotas";
    }

    //http://localhost:8099/mascotas/eliminar/
    @GetMapping("mascotas/eliminar/{id}")
    public String eliminarMascota(Model model, @PathVariable int id){
        mascotaService.deleteById(id);
        return "redirect:/mascotas/eliminar";
    }

    // Metodos privaditos =====================================================
    private void actualizarMascotasCliente(Model model) {
        List<Mascota> mascotas =  mascotaService.searchAll().stream().toList();
        List<MascotaCliente> mascotaClientes = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            Cliente cliente = clienteService.buscarClientePorMascota(mascota.getId());
            MascotaCliente mascotaCliente = new MascotaCliente(cliente.getCedula(), cliente.getNombre(), mascota);
            mascotaClientes.add(mascotaCliente);
        }
        model.addAttribute("mascotas", mascotaClientes);
    }
}
