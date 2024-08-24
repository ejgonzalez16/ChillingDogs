package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCedula(String cedula);
    @Query("SELECT c FROM Cliente c JOIN c.mascotas m WHERE m.id = :idMascota")
    Cliente findByMascotaId(Long idMascota);
}