package org.example.chillingdogspage.Controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Servicio.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Devolver datos (en JSON) en lugar de una vista (HTML)
@RequestMapping("dashboard")
// Permitir peticiones a este controlador de localhost:4200 (Angular) y del Frontend desplegado en Netlify
@CrossOrigin(origins = {"http://localhost:4200", "https://chillingdogs.netlify.app/"})
@Tag(name = "Dashboard", description = "API para traer la información del dashboard") // Tag para la documentación de la API
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    DashboardService dashboardService;

    // GET =============================================================================================================

    @GetMapping("/count-tratamiento")
    @Operation(summary = "Contar todos los tratamientos realizados")
    public ResponseEntity<Integer> countTratamientos() {
        int count = dashboardService.countTratamientos();
        logger.info("Cantidad total de tratamientos: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count-tratamiento-mes")
    @Operation(summary = "Contar los tratamientos realizados del mes")
    public ResponseEntity<Integer> countTratamientoMes() {
        int count = dashboardService.countTratamientosMes();
        logger.info("Cantidad de tratamientos del mes: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("medicamentos-mes")
    @Operation(summary = "Contar los medicamentos usados del mes")
    public ResponseEntity<List<MedicamentosMes>> medicamentosMes() {
        List<MedicamentosMes> medicamentosMes = dashboardService.medicamentosMes();
        logger.info("Medicamentos usados del mes: {}", medicamentosMes.size()); // Imprimir la cantidad de medicamentos
        return ResponseEntity.ok(medicamentosMes);
    }

    @GetMapping("count-vet-activos")
    @Operation(summary = "Contar los veterinarios activos")
    public ResponseEntity<Integer> countVetActivos() {
        int count = dashboardService.countVeterinariosActivos();
        logger.info("Cantidad de veterinarios activos: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("count-vet-inactivos")
    @Operation(summary = "Contar los veterinarios inactivos")
    public ResponseEntity<Integer> countVetInactivos() {
        int count = dashboardService.countVeterinariosInactivos();
        logger.info("Cantidad de veterinarios inactivos: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("count-mascotas")
    @Operation(summary = "Contar todas las mascotas")
    public ResponseEntity<Integer> countMascotas() {
        int count = dashboardService.countMascotas();
        logger.info("Cantidad total de mascotas: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("count-mascotas-tratamiento")
    @Operation(summary = "Contar todas las mascotas diferentes que estén en tratamiento")
    public ResponseEntity<Integer> countMascotasTratamiento() {
        int count = dashboardService.countMascotasTratamiento();
        logger.info("Cantidad de mascotas en tratamiento: {}", count); // Imprimir el conteo en consola
        return ResponseEntity.ok(count);
    }

    @GetMapping("ventas")
    @Operation(summary = "Ventas totales")
    public ResponseEntity<Double> ventas() {
        double ventas = dashboardService.ventas();
        logger.info("Ventas totales: {}", ventas); // Imprimir ventas en consola
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("ganancias")
    @Operation(summary = "Ganancias totales")
    public ResponseEntity<Double> ganancia() {
        double ganancia = dashboardService.ganancias();
        logger.info("Ganancias totales: {}", ganancia); // Imprimir ganancias en consola
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("top-drogas")
    @Operation(summary = "Top 3 drogas")
    public ResponseEntity<List<String>> topDrogas() {
        List<String> topDrogas = dashboardService.topDrogas();
        logger.info("Top 3 drogas: {}", topDrogas); // Imprimir top drogas en consola
        return ResponseEntity.ok(topDrogas);
    }
}
