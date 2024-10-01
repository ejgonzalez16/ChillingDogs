package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Collection;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Hacer el query con m.nombre en minúsculas
    @Query("SELECT m FROM Mascota m WHERE LOWER(m.nombre) LIKE %?1%")
    List<Mascota> findByNombreContaining(String nombre);

    // Hacer el query para encontrar todas las mascotas de un cliente
    @Query("SELECT m FROM Mascota m WHERE m.cliente.id = ?1")
    List<Mascota> findAllByClienteId(Long clienteId);

    // Hacer el query para encontrar todas las mascotas de un cliente
    @Query("SELECT m FROM Mascota m WHERE m.cliente.cedula = ?1")
    List<Mascota> findAllByClienteCedula(String cedula);
    Collection<Mascota> searchBySimilarName(String nombre);
    @Query("SELECT m FROM Mascota m WHERE :clienteId = m.cliente.id")
    Collection<Mascota> findByClienteId(Long clienteId);
}
