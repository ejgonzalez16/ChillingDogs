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
public class TratamientoRepositoryTest {

    @Autowired
    private TratamientoRepository tratamientoRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    @Autowired
    private DrogaRepository drogaRepository;

    @BeforeEach
    public void init() {
        Mascota mascota1 = new Mascota("Duki", "Shih tzu", 5, 15.5f, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaRepository.save(mascota1);
        Mascota mascota2 = new Mascota("Patty", "Pitbull", 3, 5.3f, "sordera", "https://www.hayvanlarim.org/wp-content/uploads/b-400476-pitbull_terrier_-150x150.jpg", "Activo", null);
        mascotaRepository.save(mascota2);
        Mascota mascota3 = new Mascota("Bengie", "Golden retriever", 6, 7.3f, "hipermetropía", "https://th.bing.com/th/id/OIP.kAMCjX7G_1inCivhWgX_7QAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaRepository.save(mascota3);
        Mascota mascota4 = new Mascota("Lucy", "Poodle", 3, 5.3f, "sordera", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaRepository.save(mascota4);

        Veterinario veterinario1 = new Veterinario("10001", "1234", "cirujano", "Anuel", "Activo", "https://avatars.githubusercontent.com/u/123321441?v=4");
        veterinarioRepository.save(veterinario1);
        Veterinario veterinario2 = new Veterinario("10002", "5678", "cirujano", "Beto", "Activo", "https://avatars.githubusercontent.com/u/123321442?v=4");
        veterinarioRepository.save(veterinario2);

        Droga droga1 = Droga.builder().nombre("Droga 1").precioCompra(10.0).precioVenta(15.0).unidadesDisponibles(5).build();
        drogaRepository.save(droga1);
        Droga droga2 = Droga.builder().nombre("Droga 2").precioCompra(20.0).precioVenta(25.0).unidadesDisponibles(10).build();
        drogaRepository.save(droga2);
        Droga droga3 = Droga.builder().nombre("Droga 3").precioCompra(30.0).precioVenta(40.0).unidadesDisponibles(0).build();
        drogaRepository.save(droga3);

        Tratamiento tratamiento1 = new Tratamiento(LocalDate.parse("2022-01-01"), mascota1, droga1, veterinario1);
        tratamientoRepository.save(tratamiento1);
        Tratamiento tratamiento2 = new Tratamiento(LocalDate.now(), mascota1, droga3, veterinario2);
        tratamientoRepository.save(tratamiento2);
        Tratamiento tratamiento3 = new Tratamiento(LocalDate.parse("2022-01-01"), mascota2, droga1, veterinario1);
        tratamientoRepository.save(tratamiento3);
        Tratamiento tratamiento4 = new Tratamiento(LocalDate.now(), mascota2, droga3, veterinario2);
        tratamientoRepository.save(tratamiento4);
        Tratamiento tratamiento5 = new Tratamiento(LocalDate.now(), mascota3, droga1, veterinario1);
        tratamientoRepository.save(tratamiento5);
    }

    @Test
    public void TratamientoRepository_findAllByMascotaId_Tratamientos() {

        // Arrange
        
        // Act
        Mascota mascota = mascotaRepository.findByNombreContaining("Duki").get(0);
        List<Tratamiento> tratamientos = tratamientoRepository.findAllByMascotaId(mascota.getId());

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos).size().isEqualTo(2);

    }

    @Test
    public void TratamientoRepository_findAllByVeterinarioId_Tratamientos() {

        // Arrange

        // Act
        Veterinario veterinario = veterinarioRepository.findByCedula("10001");
        List<Tratamiento> tratamientos = tratamientoRepository.findAllByVeterinarioId(veterinario.getId());

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos).size().isEqualTo(3);
    }

    @Test
    public void TratamientoRepository_countTratamientos_count() {

        // Arrange

        // Act
        int count = tratamientoRepository.countTratamientos();

        // Assert
        Assertions.assertThat(count).isEqualTo(5);
    }

    @Test
    public void TratamientoRepository_countTratamientosMes_count() {

        // Arrange

        // Act
        int count = tratamientoRepository.countTratamientosMes();

        // Assert
        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    public void TratamientoRepository_medicamentosMes_count() {

        // Arrange

        // Act
        List<MedicamentosMes> medicamentos = tratamientoRepository.medicamentosMes();

        // Assert
        Assertions.assertThat(medicamentos).isNotEmpty();
        Assertions.assertThat(medicamentos).size().isEqualTo(2);
    }

    @Test
    public void TratamientoRepository_countMascotasTratamiento_count() {

        // Arrange

        // Act
        int count = tratamientoRepository.countMascotasTratamiento();

        // Assert
        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    public void TratamientoRepository_ventas_numero() {

        // Arrange

        // Act
        double ventas = tratamientoRepository.ventas();

        // Assert
        Assertions.assertThat(ventas).isEqualTo(125.0);
    }

    @Test
    public void TratamientoRepository_ganancias_numero() {

        // Arrange

        // Act
        double ganancia = tratamientoRepository.ganancias();

        // Assert
        Assertions.assertThat(ganancia).isEqualTo(35.0);
    }

    @Test
    public void TratamientoRepository_topDrogas_Nombres() {

        // Arrange

        // Act
        List<MedicamentosMes> drogas = tratamientoRepository.topDrogas();

        // Assert
        Assertions.assertThat(drogas).isNotEmpty();
        Assertions.assertThat(drogas).size().isEqualTo(2);
    }

}