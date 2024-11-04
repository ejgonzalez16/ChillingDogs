package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VeterinarioService {
    public List<Veterinario> findAll();

    public Veterinario findByCedula(String cedula);

    public Veterinario createVeterinario(Veterinario veterinario);

    public Veterinario updateVeterinario(Veterinario veterinario);

    public boolean deleteVeterinario(Long id);

    List<Veterinario> findBySimilarName(String nombre);
}