package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Droga;

import java.util.List;

public interface DrogaService {
    public List<Droga> findAll();
    public Droga findById(Long id);
    public Droga registrarDroga(Droga droga);
    public Droga updateDroga(Droga droga);
    public boolean deleteDroga(Long id);
}
