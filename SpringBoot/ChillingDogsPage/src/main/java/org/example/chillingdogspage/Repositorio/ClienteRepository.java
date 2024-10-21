package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findById(long id);
    Cliente findByCedula(String cedula);

    // Hacer el query con c.nombre y nombre en minúsculas
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Cliente> findByNombreContaining_NoCaseSens(String nombre);
}