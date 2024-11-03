package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Rol;
import org.example.chillingdogspage.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);

}