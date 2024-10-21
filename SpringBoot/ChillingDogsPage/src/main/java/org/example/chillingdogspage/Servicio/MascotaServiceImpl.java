package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    MascotaRepository repository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Mascota registrarMascota(Mascota mascota) {
        return repository.save(mascota);
    }

    @Override
    public Mascota createMascota(Mascota mascota, String cedula) {
        Cliente cliente = clienteRepository.findByCedula(cedula);
        if (cliente == null) {
            return null;
        }
        mascota.setCliente(cliente);
        return repository.save(mascota);
    }

    @Override
    public List<Mascota> findAll() {
        List<Mascota> mascotas = repository.findAll();
        /*for (Mascota mascota : mascotas) {
            mascota.getCliente().setMascotas(null);
        }*/
        return mascotas;
    }

    @Override
    public Mascota findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean deleteMascota(Long id) {
        Mascota mascota = repository.findById(id).orElse(null);
        if (mascota == null) {
            return false;
        }
        // Si encontramos la mascota, desvinculamos los tratamientos asociados a esa mascota y luego la borramos
        mascota.getTratamientos().forEach(tratamiento -> tratamiento.setMascota(null));
        repository.delete(mascota);
        return true;
    }

    @Override
    public Mascota updateMascota(Mascota mascota) {
        return repository.save(mascota);
    }

    @Override
    public List<Mascota> findBySimilarName(String nombre) {
        return repository.findByNombreContaining(nombre.toLowerCase());
    }

    @Override
    public List<Mascota> findAllByClienteCedula(String cedula) {
        return repository.findAllByClienteCedula(cedula);
    }
}