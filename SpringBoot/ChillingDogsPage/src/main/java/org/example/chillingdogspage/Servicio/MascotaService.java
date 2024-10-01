package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface MascotaService{
    public List<Mascota> searchAll();
    public Mascota findById(int id);
    public List<Mascota> findByClienteId(int id);
    public void deleteById(int id);
    public void registrarMascota(Mascota mascota);

    void updateMascota(Mascota mascota);
    public List<Mascota> searchBySimilarName(String nombre);
}
