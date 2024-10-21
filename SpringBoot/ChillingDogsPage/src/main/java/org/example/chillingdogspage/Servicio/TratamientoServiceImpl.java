package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.*;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Repositorio.TratamientoRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TratamientoServiceImpl implements TratamientoService {
    @Autowired
    TratamientoRepository repository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    DrogaRepository drogaRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public List<Tratamiento> findAll() {
        return repository.findAll();
    }

    @Override
    public Tratamiento findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Tratamiento> findAllByMascotaId(Long id) {
        return repository.findAllByMascotaId(id);
    }

    @Override
    public List<Tratamiento> findAllByVeterinarioId(Long id) {
        return repository.findAllByVeterinarioId(id);
    }

    @Override
    public Tratamiento registrarTratamiento(TratamientoDTO tratamientoDTO) {
        // Obtener la droga
        Droga droga = drogaRepository.findById(tratamientoDTO.getDrogaId()).orElse(null);
        if (droga == null || droga.getUnidadesDisponibles() < 1) {
            return null;
        }
        // Usar la droga (disminuir en 1 la cantidad de unidades disponibles)
        droga.usarDroga();

        // Obtener el veterinario
        Veterinario veterinario = veterinarioRepository.findById(tratamientoDTO.getVeterinarioId()).orElse(null);
        if (veterinario == null) {
            return null;
        }

        // Obtener la mascota
        Mascota mascota = mascotaRepository.findById(tratamientoDTO.getMascotaId()).orElse(null);
        if (mascota == null) {
            return null;
        }

        // Crear el tratamiento con la fecha actual y los datos obtenidos, y guardarlo en la base de datos
        Tratamiento tratamiento = new Tratamiento(LocalDate.now(), mascota, droga, veterinario);
        return repository.save(tratamiento);
    }

    @Override
    public Tratamiento updateTratamiento(TratamientoDTO tratamientoDTO) {
        Tratamiento tratamiento = repository.findById(tratamientoDTO.getId()).orElse(null);
        if (tratamiento == null) {
            return null;
        }

        // Si la droga cambió, se actualiza la cantidad de unidades disponibles de la droga actual y la nueva
        // Además, actualizamos droga en el tratamiento
        Droga drogaActual = tratamiento.getDroga();
        if (!tratamientoDTO.getDrogaId().equals(drogaActual.getId())) {
            drogaActual.devolverDroga();
            Droga nuevaDroga = drogaRepository.findById(tratamientoDTO.getDrogaId()).orElse(null);
            if (nuevaDroga == null || nuevaDroga.getUnidadesDisponibles() < 1) {
                return null;
            }
            nuevaDroga.usarDroga();
            tratamiento.setDroga(nuevaDroga);
        }

        // Actualizar el veterinario si cambió
        Veterinario veterinarioActual = tratamiento.getVeterinario();
        if (!tratamientoDTO.getVeterinarioId().equals(veterinarioActual.getId())) {
            Veterinario nuevoVeterinario = veterinarioRepository.findById(tratamientoDTO.getVeterinarioId()).orElse(null);
            if (nuevoVeterinario == null) {
                return null;
            }
            tratamiento.setVeterinario(nuevoVeterinario);
        }

        // Actualizar la mascota si cambió
        Mascota mascotaActual = tratamiento.getMascota();
        if (!tratamientoDTO.getMascotaId().equals(mascotaActual.getId())) {
            Mascota nuevaMascota = mascotaRepository.findById(tratamientoDTO.getMascotaId()).orElse(null);
            if (nuevaMascota == null) {
                return null;
            }
            tratamiento.setMascota(nuevaMascota);
        }

        return repository.save(tratamiento);
    }

    @Override
    public boolean deleteTratamiento(Long id) {
        Tratamiento tratamiento = repository.findById(id).orElse(null);
        if (tratamiento == null) {
            return false;
        }
        /*Devolver la droga usada en el tratamiento
        tratamiento.getDroga().devolverDroga();*/
        repository.delete(tratamiento);
        return true;
    }
}