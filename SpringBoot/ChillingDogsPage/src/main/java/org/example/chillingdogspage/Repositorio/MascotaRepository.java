package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Hacer el query con m.nombre en minusculas
    @Query("SELECT m FROM Mascota m WHERE LOWER(m.nombre) LIKE %?1%")
    Collection<Mascota> searchBySimilarName(String nombre);
    @Query("SELECT m FROM Mascota m WHERE :clienteId = m.cliente.id")
    Collection<Mascota> findByClienteId(Long clienteId);
}
