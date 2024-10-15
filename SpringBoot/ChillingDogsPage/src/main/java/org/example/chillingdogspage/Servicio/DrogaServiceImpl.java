package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrogaServiceImpl implements DrogaService {
    @Autowired
    DrogaRepository drogaRepository;

    @Override
    public List<Droga> findAll() {
        return drogaRepository.findAll();
    }

    @Override
    public Droga findById(Long id) {
        return drogaRepository.findById(id).get();
    }

    @Override
    public Droga registrarDroga(Droga droga) {
        return drogaRepository.save(droga);
    }

    @Override
    public Droga updateDroga(Droga droga) {
        return drogaRepository.save(droga);
    }

    @Override
    public boolean deleteTratamiento(Long id) {
        drogaRepository.deleteById(id);
        return true;
    }
}
