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
    public List<Mascota> searchAll() {
        Collection<Mascota> mascotas = repository.findAll();
        /*for (Mascota mascota : mascotas) {
            mascota.getCliente().setMascotas(null);
        }*/
        return mascotas.stream().toList();
    }

    @Override
    public Mascota findById(int id) {
        return repository.findById((long)id).get();
    }

    @Override
    public List<Mascota> findByClienteId(int id) {
        return this.repository.findByClienteId((long)id).stream().toList();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById((long)id);
    }

    @Override
    public void updateMascota(Mascota mascota) {
        repository.save(mascota);
    }

    @Override
    public List<Mascota> searchBySimilarName(String nombre) {
        return repository.searchBySimilarName(nombre).stream().toList();
    }
}
