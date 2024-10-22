package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@ExtendWith(SpringExtension.class)     // Necesario para que se ejecuten las pruebas
public class MascotaRepositoryTest {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void init() {
        Cliente cliente1 = new Cliente("10001", "Anuel", "anuel@anu.anu", "3001234567", "https://avatars.githubusercontent.com/u/123321441?v=4");
        clienteRepository.save(cliente1);
        Cliente cliente2 = new Cliente("10002", "Maluma", "maluma@malu.malu", "3001234567", "https://avatars.githubusercontent.com/u/123321441?v=4");
        clienteRepository.save(cliente2);
        Cliente cliente3 = new Cliente("10003", "Duki", "duki@duki.duki", "3001234567", "https://avatars.githubusercontent.com/u/123321441?v=4");
        clienteRepository.save(cliente3);

        Mascota mascota1 = new Mascota("Duki", "Shih tzu", 5, 15.5f, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente1);
        mascotaRepository.save(mascota1);
        Mascota mascota2 = new Mascota("Patty", "Pitbull", 3, 5.3f, "sordera", "https://www.hayvanlarim.org/wp-content/uploads/b-400476-pitbull_terrier_-150x150.jpg", "Activo", cliente1);
        mascotaRepository.save(mascota2);
        Mascota mascota3 = new Mascota("Bengie", "Golden retriever", 6, 7.3f, "hipermetropía", "https://th.bing.com/th/id/OIP.kAMCjX7G_1inCivhWgX_7QAAAA?rs=1&pid=ImgDetMain", "Activo", cliente2);
        mascotaRepository.save(mascota3);
        Mascota mascota4 = new Mascota("Duki", "Poodle", 3, 5.3f, "sordera", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente3);
        mascotaRepository.save(mascota4);
    }

    @Test
    public void MascotaRepository_findByNombreContaining_Mascotas() {
        // Arrange

        // Act
        List<Mascota> mascotas = mascotaRepository.findByNombreContaining("Du");

        // Assert
        Assertions.assertThat(mascotas).isNotEmpty();
        Assertions.assertThat(mascotas).size().isEqualTo(2);
    }

    @Test
    public void MascotaRepository_findAllByClienteId_Mascotas() {
        // Arrange

        // Act
        Cliente cliente = clienteRepository.findByCedula("10001");
        List<Mascota> mascotas = mascotaRepository.findAllByClienteId(cliente.getId());

        // Assert
        Assertions.assertThat(mascotas).isNotEmpty();
        Assertions.assertThat(mascotas).size().isEqualTo(2);
    }

    @Test
    public void MascotaRepository_findAllByClienteCedula_Mascotas() {
        // Arrange

        // Act
        List<Mascota> mascotas = mascotaRepository.findAllByClienteCedula("10002");

        // Assert
        Assertions.assertThat(mascotas).isNotEmpty();
        Assertions.assertThat(mascotas).size().isEqualTo(1);
    }

    @Test
    public void MascotaRepository_countMascotas_cantidad() {
        // Arrange

        // Act
        int count = mascotaRepository.countMascotas();

        // Assert
        Assertions.assertThat(count).isEqualTo(4);
    }

    
}
