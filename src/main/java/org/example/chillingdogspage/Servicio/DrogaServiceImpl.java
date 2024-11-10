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
        List<Object[]> drogasConUnidadesVendidas = drogaRepository.findDrogasConUnidadesVendidas();
        // Poner las unidades vendidas en cada droga con map
        return drogasConUnidadesVendidas.stream().map(droga -> {
            Droga d = (Droga) droga[0];
            Long unidadesVendidas = (Long) droga[1];
            d.setUnidadesVendidas(unidadesVendidas);
            return d;
        }).toList();
    }

    @Override
    public List<Droga> findDisponibles() {
        return drogaRepository.findDisponibles();
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
    public boolean deleteDroga(Long id) {
        drogaRepository.deleteById(id);
        return true;
    }
}
