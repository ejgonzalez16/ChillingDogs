package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Servicio.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("dashboard")
@CrossOrigin(origins = "http://localhost:4200") // Solo la aplicación en Angular puede realizar peticiones a este controlador
@Tag(name = "Dashboard", description = "API para traer la información del dashboard")  // Tag para la documentación de la API
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    // GET =============================================================================================================
    //http://localhost:8099/admin/count-tratamiento
    @PostMapping("/count-tratamiento")
    @Operation(summary = "Contar todos los tratamientos realizados")
    public ResponseEntity<Integer> countTratamientos() {
        int count = dashboardService.countTratamientos();
        return ResponseEntity.ok(count);
    }

    //http://localhost:8099/admin/count-tratamiento-mes
    @PostMapping("/count-tratamiento-mes")
    @Operation(summary = "Contar los tratamientos realizados del mes")
    public ResponseEntity<Integer> countTratamientoMes() {
        int count = dashboardService.countTratamientosMes();
        return ResponseEntity.ok(count);
    }

    //http://localhost:8099/admin/medicamentos-mes
    @PostMapping("medicamentos-mes")
    @Operation(summary = "Contar los medicaemntos usados del mes")
    public ResponseEntity<List<MedicamentosMes>> medicamentosMes() {
        List<MedicamentosMes> medicamentosMes = dashboardService.medicamentosMes();
        return ResponseEntity.ok(medicamentosMes);
    }
    
    //http://localhost:8099/admin/count-vet-activos
    @PostMapping("count-vet-activos")
    @Operation(summary = "Contar los veterinarios activos")
    public ResponseEntity<Integer> countVetActivos() {
        int count = dashboardService.countVeterinariosActivos();
        return ResponseEntity.ok(count);
    }


    //http://localhost:8099/admin/count-vet-inactivos
    @PostMapping("count-vet-inactivos")
    @Operation(summary = "Contar los veterinarios inactivos")
    public ResponseEntity<Integer> countVetInactivos() {
        int count = dashboardService.countVeterinariosInactivos();
        return ResponseEntity.ok(count);
    }

    //http://localhost:8099/dashboard/count-mascotas
    @PostMapping("count-mascotas")
    @Operation(summary = "Contar todas las mascotas")
    public ResponseEntity<Integer> countMascotas() {
        int count = dashboardService.countMascotas();
        return ResponseEntity.ok(count);
    }

    //http://localhost:8099/dashboard/count-mascotas-tratamiento
    @PostMapping("count-mascotas-tratamiento")
    @Operation(summary = "Contar todas las mascotas diferentes que esten en tratamiento")
    public ResponseEntity<Integer> countMascotasTratamiento() {
        int count = dashboardService.countMascotasTratamiento();
        return ResponseEntity.ok(count);
    }

    //http://localhost:8099/dashboard/ventas
    @PostMapping("ventas")
    @Operation(summary = "Ventas totales")
    public ResponseEntity<Double> ventas() {
        double ventas = dashboardService.ventas();
        return ResponseEntity.ok(ventas);
    }

    //http://localhost:8099/dashboard/ganancias
    @PostMapping("ganancias")
    @Operation(summary = "Ganancias totales")
    public ResponseEntity<Double> ganancia() {
        double ganancia = dashboardService.ganancias();
        return ResponseEntity.ok(ganancia);
    }

    //http://localhost:8099/dashboard/top-drogas
    @PostMapping("top-drogas")
    @Operation(summary = "Top 3 drogas")
    public ResponseEntity<List<String>> topDrogas() {
        List<String> topDrogas = dashboardService.topDrogas();
        return ResponseEntity.ok(topDrogas);
    }
}