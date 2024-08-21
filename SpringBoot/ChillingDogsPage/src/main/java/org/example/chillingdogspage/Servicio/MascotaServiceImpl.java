package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    MascotaRepository repository;

    @Override
    public void registrarMascota(Mascota mascota) {
        repository.save(mascota);
    }

    @Override
    public Collection<Mascota> searchAll() {
        return repository.findAll();
    }

    @Override
    public Mascota findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
