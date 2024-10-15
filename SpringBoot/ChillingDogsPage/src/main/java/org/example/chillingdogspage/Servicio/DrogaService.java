package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Entidad.Tratamiento;
import org.example.chillingdogspage.Entidad.TratamientoDTO;

import java.util.List;

public interface DrogaService {
    public List<Droga> findAll();
    public Droga findById(Long id);
    public Droga registrarDroga(Droga droga);
    public Droga updateDroga(Droga droga);
    public boolean deleteTratamiento(Long id);
}
