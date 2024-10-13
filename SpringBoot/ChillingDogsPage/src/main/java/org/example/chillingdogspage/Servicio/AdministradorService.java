package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministradorService {
    public Administrador findByCedulaAndContrasena(String cedula, String contrasena);
}