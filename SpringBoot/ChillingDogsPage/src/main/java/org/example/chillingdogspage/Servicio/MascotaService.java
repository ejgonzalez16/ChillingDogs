package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MascotaService{
    public List<Mascota> findAll();
    public Mascota findById(Long id);
    public List<Mascota> findBySimilarName(String nombre);
    List<Mascota> findAllByClienteCedula(String cedula);
    public Mascota createMascota(Mascota mascota, String cedula);
    public Mascota registrarMascota(Mascota mascota);
    Mascota updateMascota(Mascota mascota);
    public void deleteMascota(Long id);
}