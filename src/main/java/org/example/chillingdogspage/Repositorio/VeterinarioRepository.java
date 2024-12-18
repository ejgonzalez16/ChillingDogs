package org.example.chillingdogspage.Repositorio;

import org.example.chillingdogspage.Entidad.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Veterinario findById(long id);
    Veterinario findByCedula(String cedula);

    // Hacer el query con c.nombre en minúsculas
    @Query("SELECT c FROM Veterinario c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Veterinario> findByNombreContaining(String nombre);

    // Query para contar los veterinarios que estan activos
    @Query("SELECT COUNT(*) FROM Veterinario v WHERE v.estado = 'activo'")
    int countVeterinariosActivos();

    // Query para contar los veterinarios que estan inactivos
    @Query("SELECT COUNT(*) FROM Veterinario v WHERE v.estado = 'inactivo'")
    int countVeterinariosInactivos();
}