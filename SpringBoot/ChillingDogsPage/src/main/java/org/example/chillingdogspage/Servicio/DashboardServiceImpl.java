package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Repositorio.AdministradorRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Repositorio.TratamientoRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.example.chillingdogspage.Repositorio.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    TratamientoRepository repoTratamientos;
    @Autowired
    VeterinarioRepository repoVeterinarios;
    @Autowired
    MascotaRepository repoMascotas;

    @Override
    public int countTratamientos() {
        return repoTratamientos.countTratamientos();
    }

    @Override
    public int countTratamientosMes() {
        return repoTratamientos.countTratamientosMes();
    }

    @Override
    public List<MedicamentosMes> medicamentosMes() {
        return repoTratamientos.medicamentosMes();
    }

    @Override
    public int countVeterinariosActivos() {
        return repoVeterinarios.countVeterinariosActivos();
    }

    @Override
    public int countVeterinariosInactivos() {
        return repoVeterinarios.countVeterinariosInactivos();
    }

    @Override
    public int countMascotas() {
        return repoMascotas.countMascotas();
    }

    @Override
    public int countMascotasTratamiento() {
        return repoTratamientos.countMascotasTratamiento();
    }

    @Override
    public double ventas() {
        return repoTratamientos.ventas();
    }

    @Override
    public double ganancias() {
        return repoTratamientos.ganancias();
    }

    @Override
    public List<String> topDrogas() {
        return repoTratamientos.topDrogas();
    }

    
}