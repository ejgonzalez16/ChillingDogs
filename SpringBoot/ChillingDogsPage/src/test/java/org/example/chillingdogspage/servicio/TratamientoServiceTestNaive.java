package org.example.chillingdogspage.servicio;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Entidad.Tratamiento;
import org.example.chillingdogspage.Entidad.TratamientoDTO;
import org.example.chillingdogspage.Entidad.Veterinario;
import org.example.chillingdogspage.Servicio.DrogaService;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.example.chillingdogspage.Servicio.TratamientoService;
import org.example.chillingdogspage.Servicio.VeterinarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

// Implica un mayor tiempo en cada prueba
@SpringBootTest // Crea un contexto de Spring (instancia de la aplicación) para las pruebas
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Reinicia el contexto de Spring antes de cada prueba
@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
public class TratamientoServiceTestNaive {
    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private DrogaService drogaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @BeforeEach
    public void init() {
        Mascota mascota1 = new Mascota("Duki", "Shih tzu", 5, 15.5f, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaService.registrarMascota(mascota1);
        Mascota mascota2 = new Mascota("Patty", "Pitbull", 3, 5.3f, "sordera", "https://www.hayvanlarim.org/wp-content/uploads/b-400476-pitbull_terrier_-150x150.jpg", "Activo", null);
        mascotaService.registrarMascota(mascota2);
        Mascota mascota3 = new Mascota("Bengie", "Golden retriever", 6, 7.3f, "hipermetropía", "https://th.bing.com/th/id/OIP.kAMCjX7G_1inCivhWgX_7QAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaService.registrarMascota(mascota3);

        Veterinario veterinario1 = new Veterinario("10001", "1234", "cirujano", "Anuel", "Activo", "https://avatars.githubusercontent.com/u/123321441?v=4");
        veterinarioService.createVeterinario(veterinario1);
        Veterinario veterinario2 = new Veterinario("10002", "5678", "cirujano", "Beto", "Activo", "https://avatars.githubusercontent.com/u/123321442?v=4");
        veterinarioService.createVeterinario(veterinario2);

        Droga droga1 = new Droga("Droga 1", 15.0, 10.0, 5);
        drogaService.registrarDroga(droga1);
        Droga droga2 = new Droga("Droga 2", 25.0, 20.0, 10);
        drogaService.registrarDroga(droga2);
        Droga droga3 = new Droga("Droga 3", 40.0, 30.0, 0);
        drogaService.registrarDroga(droga3);

        TratamientoDTO tratamiento1 = new TratamientoDTO(1L, 1L, 1L, 1L);
        TratamientoDTO tratamiento2 = new TratamientoDTO(2L, 2L, 2L, 1L);
        TratamientoDTO tratamiento3 = new TratamientoDTO(3L, 3L, 3L, 2L);
        TratamientoDTO tratamiento4 = new TratamientoDTO(4L, 1L, 2L, 1L);
        TratamientoDTO tratamiento5 = new TratamientoDTO(5L, 1L, 2L, 2L);

        tratamientoService.registrarTratamiento(tratamiento1);
        tratamientoService.registrarTratamiento(tratamiento2);
        tratamientoService.registrarTratamiento(tratamiento3);
        tratamientoService.registrarTratamiento(tratamiento4);
        tratamientoService.registrarTratamiento(tratamiento5);
    }

    @Test
    public void tratamientoService_registrarTratamiento_Tratamiento() {

        // Arrange
        Mascota mascota = new Mascota("Lucy", "Poodle", 3, 5.3f, "sordera", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", null);
        mascotaService.registrarMascota(mascota);
        Veterinario veterinario = new Veterinario("10010", "5678", "cirujano", "Paco", "Activo", "https://avatars.githubusercontent.com/u/123321442?v=4");
        veterinarioService.createVeterinario(veterinario);
        Droga droga = new Droga("Droga 4", 12.0, 9.0, 3);
        drogaService.registrarDroga(droga);
        // Act
        TratamientoDTO tratamiento = new TratamientoDTO(5L,4L,4L,3L);
        Tratamiento nuevoTratamiento = tratamientoService.registrarTratamiento(tratamiento);

        // Assert
        Assertions.assertThat(nuevoTratamiento).isNotNull();
    }

    @Test
    public void tratamientoService_findAllByVeterinarioId_Tratamientos() {

        // Arrange

        // Act
        List<Tratamiento> tratamientos = tratamientoService.findAllByVeterinarioId(1L);

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos).size().isEqualTo(3);
    }

    @Test
    public void tratamientoService_findAllByMascotaId_Tratamientos() {

        // Arrange

        // Act
        List<Tratamiento> tratamientos = tratamientoService.findAllByMascotaId(1L);

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos).size().isEqualTo(3);
    }

    @Test
    public void tratamientoService_findById_Tratamiento() {

        // Arrange

        // Act
        Tratamiento tratamiento = tratamientoService.findById(1L);

        // Assert
        Assertions.assertThat(tratamiento).isNotNull();
        Assertions.assertThat(tratamiento.getMascota().getId()).isEqualTo(1L);
        Assertions.assertThat(tratamiento.getVeterinario().getId()).isEqualTo(1L);
    }

    @Test
    public void tratamientoService_findAll_Tratamientos() {

        // Arrange

        // Act
        List<Tratamiento> tratamientos = tratamientoService.findAll();

        // Assert
        Assertions.assertThat(tratamientos).isNotEmpty();
        Assertions.assertThat(tratamientos).size().isEqualTo(4);
    }
}
