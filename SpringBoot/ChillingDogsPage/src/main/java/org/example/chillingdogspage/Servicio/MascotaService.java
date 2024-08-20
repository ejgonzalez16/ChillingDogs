package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface MascotaService{
    public Collection<Mascota> searchAll();
    public Mascota findById(int id);
    public void deleteById(int id);
    public void registrarMascota(Mascota mascota);
}
