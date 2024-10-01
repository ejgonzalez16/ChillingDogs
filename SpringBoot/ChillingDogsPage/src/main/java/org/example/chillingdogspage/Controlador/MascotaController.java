package org.example.chillingdogspage.Controlador;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.ErrorHandling.ClientNotFoundException;
import org.example.chillingdogspage.Servicio.ClienteService;
import org.springframework.ui.Model;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("mascotas")
public class MascotaController {
    @Autowired
    MascotaService mascotaService;
    @Autowired
    ClienteService clienteService;
    String carpeta = "mascotas/";

    // URLs del Cliente =====================================================
    // Retrieve =============================================================
    //http://localhost:8099/mascotas/all
    @GetMapping("all")
    public List<Mascota> getMascotas(){
        return this.mascotaService.searchAll();
    }

    /*
    //http://localhost:8099/mascotas/mis-mascotas/{cedula}
    @GetMapping("mis-mascotas/{cedula}")
    public String misMascotas(Model model, @PathVariable String cedula){
        Cliente cliente = clienteService.buscarCliente(cedula);
        if(cliente == null){
            throw new ClientNotFoundException(cedula);
        }
        model.addAttribute("cliente", cliente);
        return carpeta + "mis_mascotas";
    }*/

    //http://localhost:8099/mascotas/{id}
    @GetMapping("{id}")
    public Mascota miMascota(@PathVariable int id){
        return this.mascotaService.findById(id);
    }

    @GetMapping("cliente/{id}")
    public List<Mascota> findByClienteId(@PathVariable int id){
        return this.mascotaService.findByClienteId(id);
    }

    //http://localhost:8099/mascotas/add
    @PostMapping("add")
    public void addMascota(@RequestBody Mascota mascota){
        this.mascotaService.registrarMascota(mascota);
    }

    @PutMapping("update/{id}")
    public void updateMascota(@RequestBody Mascota mascota){
        this.mascotaService.registrarMascota(mascota);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable int id){
        this.mascotaService.deleteById(id);
    }

    // URLs del Veterinario =====================================================
    // Create =============================================================
    @GetMapping("registrar")
    public String registrarMascota(Model model) {
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clienteService.obtenerClientes());
        return carpeta + "registrar_mascota";
    }

    @PostMapping("registrar")
    public String registrarMascota(Mascota mascota) {
        mascota.setCliente(clienteService.buscarCliente(mascota.getCliente().getCedula()));
        mascotaService.registrarMascota(mascota);
        return "redirect:/mascotas/buscar";
    }

    // Retrieve =============================================================
    //http://localhost:8099/mascotas/buscar
    @GetMapping("buscar")
    public String buscar(Model model){
        actualizarMascotasCliente(model);
        return carpeta + "buscar_mascotas";
    }

    //http://localhost:8099/mascotas/buscar
    @PostMapping("buscar")
    public String buscarPorNombre(Model model, @RequestParam("nombrePerro") String nombre){
        // Poner el nombre en minúsculas
        nombre = nombre.toLowerCase();
        List<Mascota> mascotas =  mascotaService.searchBySimilarName(nombre).stream().toList();
        if (mascotas.isEmpty()) {
            return "redirect:/mascotas/buscar";
        }

        model.addAttribute("mascotas", mascotas);
        return carpeta + "buscar_mascotas";
    }

    //http://localhost:8099/mascotas/detalles-completos/{id}
    @GetMapping("detalles-completos/{id}")
    public String detallesCompletos(Model model, @PathVariable int id){
        Mascota mascota =  mascotaService.findById(id);
        model.addAttribute("mascota", mascota);
        return carpeta + "detalles_mascota";
    }

    // Update =============================================================
    @GetMapping("modificar/{id}")
    public String modificarMascota(@PathVariable("id") Integer id, Model model) {
        Mascota mascota = mascotaService.findById(id);
        model.addAttribute("mascota", mascota);
        return carpeta + "modificar_mascota";
    }
    @PostMapping("modificar/{id}")
    public String modificarMascota(@PathVariable("id") Integer id, Mascota mascota) {
        mascotaService.updateMascota(mascota);
        return "redirect:/mascotas/buscar";
    }

    // Delete =============================================================
    //http://localhost:8099/mascotas/eliminar/
    @GetMapping("eliminar/{id}")
    public String eliminarMascota(Model model, @PathVariable int id){
        mascotaService.deleteById(id);
        return "redirect:/mascotas/buscar";
    }

    // Metodos privaditos =====================================================
    private void actualizarMascotasCliente(Model model) {
        List<Mascota> mascotas =  mascotaService.searchAll().stream().toList();
        model.addAttribute("mascotas", mascotas);
    }
}
