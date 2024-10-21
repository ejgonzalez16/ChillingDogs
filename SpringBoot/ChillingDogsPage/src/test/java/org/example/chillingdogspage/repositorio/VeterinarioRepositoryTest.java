package org.example.chillingdogspage.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Entidad.MedicamentosMes;
import org.example.chillingdogspage.Entidad.Tratamiento;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Repositorio.TratamientoRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@ExtendWith(SpringExtension.class)     // Necesario para que se ejecuten las pruebas
public class VeterinarioRepositoryTest {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @BeforeEach
    void init() {
        Veterinario veterinario = new Veterinario("10001", "contrasena", "especialidad", "Juan", "activo", "foto");
        veterinarioRepository.save(veterinario);
        Veterinario veterinario2 = new Veterinario("10002", "contrasena", "especialidad", "Juanito", "inactivo", "foto");
        veterinarioRepository.save(veterinario2);
        Veterinario veterinario3 = new Veterinario("10003", "contrasena", "especialidad", "Paco", "activo", "foto");
        veterinarioRepository.save(veterinario3);
    }

    @Test
    public void VeterinarioRepository_findByCedulaAndContrasena_Veterinario() {

        // Arrange

        // Act
        Veterinario veterinario = veterinarioRepository.findByCedulaAndContrasena("10001", "contrasena");

        // Assert
        Assertions.assertThat(veterinario).isNotNull();
        
    }

    @Test
    public void VeterinarioRepository_findByNombreContaining_Veterinario() {

        // Arrange

        // Act
        List<Veterinario> veterinarios = veterinarioRepository.findByNombreContaining("ju");

        // Assert
        Assertions.assertThat(veterinarios).isNotEmpty();
        Assertions.assertThat(veterinarios).size().isEqualTo(2);
    }

    @Test
    public void VeterinarioRepository_countVeterinariosActivos_count() {

        // Arrange

        // Act
        int count = veterinarioRepository.countVeterinariosActivos();

        // Assert
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    public void VeterinarioRepository_countVeterinariosInactivos_count() {

        // Arrange

        // Act
        int count = veterinarioRepository.countVeterinariosInactivos();

        // Assert
        Assertions.assertThat(count).isEqualTo(1);
    }
    
}
