package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@RunWith(SpringRunner.class)    // Necesario para que se ejecuten las pruebas
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    /*
    Notación para pruebas:
    ClaseProbando_metodoProbando_resultadoEsperado

    Patrón para pruebas:
    1. Arrange: preparar lo necesario para la prueba
    2. Act: usar la ClaseProbando.metodoProbando
    3. Assert: verificar que el resultado es el esperado
     */

    @BeforeEach   // Se ejecuta antes de cada prueba
    public void init() {
        Cliente cliente1 = clienteRepository.save(new Cliente("10001", "Anuel", "anuel@anu.anu", "3001234567", "https://avatars.githubusercontent.com/u/123321441?v=4"));
        Cliente cliente2 = clienteRepository.save(new Cliente("10002", "Beto", "beto@beto.beto", "3002345678", "https://avatars.githubusercontent.com/u/123321442?v=4"));
        Cliente cliente3 = clienteRepository.save(new Cliente("10003", "Carlos", "carlos@car.car", "3003456789", "https://avatars.githubusercontent.com/u/123321443?v=4"));
        Cliente cliente4 = clienteRepository.save(new Cliente("10004", "Dario", "dario@dar.dar", "3004567890", "https://avatars.githubusercontent.com/u/123321444?v=4"));

        mascotaRepository.save(new Mascota("Aby", "Shih tzu", 4, 4.3f, "daltonismo", "https://cdn.britannica.com/03/234203-050-C3D47B4B/Shih-tzu-dog.jpg", "Activo", cliente1));
        mascotaRepository.save(new Mascota("Boby", "Pitbull", 3, 5.3f, "sordera", "https://www.hayvanlarim.org/wp-content/uploads/b-400476-pitbull_terrier_-150x150.jpg", "Activo", cliente2));
        mascotaRepository.save(new Mascota("Coby", "Pastor alemán", 5, 6.3f, "miopía", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente3));
        mascotaRepository.save(new Mascota("Doby", "Golden retriever", 6, 7.3f, "hipermetropía", "https://th.bing.com/th/id/OIP.kAMCjX7G_1inCivhWgX_7QAAAA?rs=1&pid=ImgDetMain", "Activo", cliente4));
    }

    @Test
    public void ClienteRepository_save_Cliente(){
        // Arrange
        Cliente cliente = new Cliente("12345", "Manuel", "manuel@manu.manu", "3009835434", "https://avatars.githubusercontent.com/u/123321442?v=4");
        // Act
        Cliente clienteGuardado = clienteRepository.save(cliente);
        // Assert
        Assertions.assertThat(clienteGuardado).isNotNull();
    }

    @Test
    public void ClienteRepository_findAll_NotEmptyList() {
        // Arrange
        Cliente cliente = new Cliente("12345", "Manuel", "manuel@manu.manu", "3009835434", "https://avatars.githubusercontent.com/u/123321442?v=4");

        // Act
        clienteRepository.save(cliente);
        List<Cliente> clientes = clienteRepository.findAll();

        // Assert
        Assertions.assertThat(clientes).isNotNull();
        Assertions.assertThat(clientes.size()).isEqualTo(5);
    }

    @Test
    public void ClienteRepository_findById_FindWrongIndex() {
        // Arrange
        Long index = -1L;

        // Act
        Optional<Cliente> cliente = clienteRepository.findById(index);

        // Assert
        Assertions.assertThat(cliente).isEmpty();   // Cuando el Optional está vacío se usa isEmpty()
    }

    @Test
    public void ClienteRepository_deleteById_EmptyCliente() {
        // Arrange
        Long index = 2L;

        // Act
        clienteRepository.deleteById(index);

        // Assert
        Assertions.assertThat(clienteRepository.findById(index)).isEmpty();
    }

    @Test
    public void ClienteRepository_findByCedula_FindWrongCedula() {
        // Arrange
        String cedula = "12345";

        // Act
        Cliente cliente = clienteRepository.findByCedula(cedula);

        // Assert
        Assertions.assertThat(cliente).isNull();
    }

    @Test
    public void ClienteRepository_findByNombreContaining_Cliente() {
        // Arrange
        String nombre = "Anuel";

        // Act
        List<Cliente> clientes = clienteRepository.findByNombreContaining(nombre);

        // Assert
        Assertions.assertThat(clientes).isNotNull();
    }

    @Test
    public void ClienteRepository_updateByName_Cliente() {
        // Arrange
        String nombre = "Beto";

        // Act
        Cliente cliente = clienteRepository.findByNombreContaining(nombre).get(0);
        cliente.setNombre("Beto actualizado");
        Cliente clienteActualizado = clienteRepository.save(cliente);

        // Assert
        Assertions.assertThat(clienteActualizado).isNotNull();
        Assertions.assertThat(clienteActualizado.getNombre()).isEqualTo("Beto actualizado");
    }
}
