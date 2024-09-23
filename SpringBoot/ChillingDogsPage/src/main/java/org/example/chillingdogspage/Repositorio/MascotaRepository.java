package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Hacer el query con m.nombre en minusculas
    @Query("SELECT m FROM Mascota m WHERE LOWER(m.nombre) LIKE %?1%")
    Collection<Mascota> searchBySimilarName(String nombre);

    // Hacer el query para encontrar todas las mascotas de un cliente
    @Query("SELECT m FROM Mascota m WHERE m.cliente.id = ?1")
    Collection<Mascota> findAllByClienteId(Long clienteId);
}
