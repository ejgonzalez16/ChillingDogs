package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByCedula(String cedula);
}