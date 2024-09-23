package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

}