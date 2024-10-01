package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collection;
import java.util.List;

@Service
public interface MascotaService{
    public List<Mascota> findAll();
    public Mascota findById(Long id);
    public List<Mascota> findBySimilarName(String nombre);
    List<Mascota> findAllByClienteCedula(String cedula);
    public Mascota createMascota(Mascota mascota, String cedula);
    Mascota updateMascota(Mascota mascota);
    public void deleteMascota(Long id);
    public List<Mascota> searchAll();
    public Mascota findById(int id);
    public List<Mascota> findByClienteId(int id);
    public void deleteById(int id);
    public void registrarMascota(Mascota mascota);

    void updateMascota(Mascota mascota);
    public List<Mascota> searchBySimilarName(String nombre);
}
