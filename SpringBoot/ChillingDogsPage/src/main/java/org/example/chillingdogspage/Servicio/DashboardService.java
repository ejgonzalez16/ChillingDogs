package org.example.chillingdogspage.Servicio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DashboardService {
    public int countTratamientos();
    public int countTratamientosMes();
    public List<MedicamentosMes> medicamentosMes();
    public int countVeterinariosActivos();
    public int countVeterinariosInactivos();
    public int countMascotas();
    public int countMascotasTratamiento();
    public double ventas();
    public double ganancias();
    public List<String> topDrogas();
}