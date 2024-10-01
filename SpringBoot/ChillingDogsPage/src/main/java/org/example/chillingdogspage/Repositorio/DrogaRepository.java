package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Droga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {

}