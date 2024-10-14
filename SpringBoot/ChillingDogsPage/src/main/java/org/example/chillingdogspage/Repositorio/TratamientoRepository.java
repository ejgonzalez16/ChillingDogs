package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Entidad.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    // Hacer el query para encontrar todos los tratamientos de una mascota
    @Query("SELECT t FROM Tratamiento t WHERE t.mascota.id = ?1")
    List<Tratamiento> findAllByMascotaId(Long idMascota);

    // Hacer el query para encontrar todos los tratamientos de un veterinario
    @Query("SELECT t FROM Tratamiento t WHERE t.veterinario.id = ?1")
    List<Tratamiento> findAllByVeterinarioId(Long idVeterinario);

    // Query para contar los tratamientos realizados
    @Query("SELECT COUNT(*) FROM Tratamiento")
    int countTratamientos();

    // Query para contar los tratamientos del último mes
    @Query("SELECT COUNT(*) FROM Tratamiento WHERE MONTH(fecha) = MONTH(CURDATE()) AND YEAR(fecha) = YEAR(CURDATE())")
    int countTratamientosMes();

    // Query para traer una pareja del nombre de la droga administrada
    // Y el número de veces que fue administrada en el último mes
    @Query("SELECT new org.example.chillingdogspage.Entidad.MedicamentosMes(d.nombre, COUNT(*)) FROM Tratamiento t JOIN t.droga d WHERE MONTH(t.fecha) = MONTH(CURDATE()) AND YEAR(t.fecha) = YEAR(CURDATE()) GROUP BY d.id")
    List<MedicamentosMes> medicamentosMes();

    // Query para contar todas las mascotas diferentes que estén en tratamiento
    @Query("SELECT COUNT(DISTINCT m.id) FROM Tratamiento t JOIN t.mascota m")
    int countMascotasTratamiento();

    // Query para calcular las ventas totales de las drogas de los tratamientos
    @Query("SELECT SUM(d.precioVenta) FROM Tratamiento t JOIN t.droga d")
    double ventas();

    // Query para calcular las ganancias totales de las drogas de los tratamientos
    @Query("SELECT SUM(d.precioVenta - d.precioCompra) FROM Tratamiento t JOIN t.droga d")
    double ganancias();

    // Query para traer el top 3 drogas
    @Query("SELECT d.nombre FROM Tratamiento t JOIN t.droga d GROUP BY d.id ORDER BY COUNT(*) DESC LIMIT 3")
    List<String> topDrogas();
}