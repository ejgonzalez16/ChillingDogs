package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MascotaService{
    public Mascota registrarMascota(Mascota mascota);
    public List<Mascota> findAll();
    public Mascota findById(Long id);
    public List<Mascota> findBySimilarName(String nombre);
    List<Mascota> findAllByClienteCedula(String cedula);
    Mascota updateMascota(Mascota mascota);
    public boolean deleteMascota(Long id);
}