package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Hacer el query con m.nombre en minúsculas
    @Query("SELECT m FROM Mascota m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Mascota> findByNombreContaining_NoCaseSens(String nombre);

    // Hacer el query para encontrar todas las mascotas de un cliente
    @Query("SELECT m FROM Mascota m WHERE m.cliente.id = ?1")
    List<Mascota> findAllByClienteId(Long clienteId);

    // Hacer el query para encontrar todas las mascotas de un cliente
    @Query("SELECT m FROM Mascota m WHERE m.cliente.cedula = ?1")
    List<Mascota> findAllByClienteCedula(String cedula);

    // Query para contar todas las mascotas
    @Query("SELECT COUNT(*) FROM Mascota")
    int countMascotas();
}