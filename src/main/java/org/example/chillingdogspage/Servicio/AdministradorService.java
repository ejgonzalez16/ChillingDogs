package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.springframework.stereotype.Service;

@Service
public interface AdministradorService {
    Administrador findByCedula(String cedula);
}