package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MascotaService{
    public List<Mascota> searchAll();
    public Mascota findById(Long id);
    public void deleteById(Long id);
    public Mascota createMascota(Mascota mascota, String cedula);

    Mascota updateMascota(Mascota mascota);
    public List<Mascota> searchBySimilarName(String nombre);
    List<Mascota> findAllByClienteCedula(String cedula);
}
