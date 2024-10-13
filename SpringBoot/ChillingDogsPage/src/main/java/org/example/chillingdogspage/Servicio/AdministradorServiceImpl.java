package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Repositorio.AdministradorRepository;
import org.example.chillingdogspage.Repositorio.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    AdministradorRepository repository;

    @Override
    public Administrador findByCedulaAndContrasena(String cedula, String contrasena){
        return repository.findByCedulaAndContrasena(cedula, contrasena);
    }
}