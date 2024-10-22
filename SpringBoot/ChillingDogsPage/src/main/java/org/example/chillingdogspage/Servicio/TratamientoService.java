package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Tratamiento;
import org.example.chillingdogspage.Entidad.TratamientoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TratamientoService {
    public List<Tratamiento> findAll();
    public Tratamiento findById(Long id);
    public List<Tratamiento> findAllByMascotaId(Long id);
    public List<Tratamiento> findAllByVeterinarioId(Long id);
    public Tratamiento registrarTratamiento(TratamientoDTO tratamientoDTO);
    // public Tratamiento updateTratamiento(TratamientoDTO tratamientoDTO);
    // public boolean deleteTratamiento(Long id);
}