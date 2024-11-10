package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Droga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {
    // Query para encontrar las drogas disponibles (unidadesDisponibles > 0)
    @Query("SELECT d FROM Droga d WHERE d.unidadesDisponibles > 0")
    public List<Droga> findDisponibles();

    // Query para traer todas las drogas con sus unidades vendidas (que corresponde a la cantidad de tratamientos que se han realizado con esa droga)
    // Pero también traer las drogas que no han sido usadas en ningún tratamiento
    @Query("SELECT d, COUNT(t) FROM Droga d LEFT JOIN Tratamiento t ON d.id = t.droga.id GROUP BY d")
    public List<Object[]> findDrogasConUnidadesVendidas();
}