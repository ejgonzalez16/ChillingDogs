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
}