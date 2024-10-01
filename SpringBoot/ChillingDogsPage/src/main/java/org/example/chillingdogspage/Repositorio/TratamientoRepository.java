package org.example.chillingdogspage.Repositorio;

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
}