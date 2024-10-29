package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    // Hacer el query para encontrar que concuerde cedula y contrasena
    @Query("SELECT a FROM Administrador a WHERE a.cedula = ?1 AND a.contrasena = ?2")
    Administrador findByCedulaAndContrasena(String cedula, String contrasena);

}