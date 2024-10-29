package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@ExtendWith(SpringExtension.class)    // Necesario para que se ejecuten las pruebas
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    /*
    Notación para pruebas:
    ClaseProbando_metodoProbando_resultadoEsperado

    Patrón para pruebas:
    1. Arrange: preparar lo necesario para la prueba
    2. Act: usar la ClaseProbando.metodoProbando
    3. Assert: verificar que el resultado es el esperado
     */

    @BeforeEach   // Se ejecuta antes de cada prueba
    void init() {
        clienteRepository.save(new Cliente("10001", "Anuel", "anuel@anu.anu", "3001234567", "https://avatars.githubusercontent.com/u/123321441?v=4"));
        clienteRepository.save(new Cliente("10002", "Beto", "beto@beto.beto", "3002345678", "https://avatars.githubusercontent.com/u/123321442?v=4"));
        clienteRepository.save(new Cliente("10003", "Carlos", "carlos@car.car", "3003456789", "https://avatars.githubusercontent.com/u/123321443?v=4"));
        clienteRepository.save(new Cliente("10004", "Dario", "dario@dar.dar", "3004567890", "https://avatars.githubusercontent.com/u/123321444?v=4"));
    }

    // Pruebas Create --------------------------------------------------------------------------------------------------
    @Test
    public void ClienteRepository_save_Cliente(){
        // Arrange
        Cliente cliente = new Cliente("12345", "Manuel", "manuel@manu.manu", "3009835434", "https://avatars.githubusercontent.com/u/123321442?v=4");
        // Act
        Cliente clienteGuardado = clienteRepository.save(cliente);
        // Assert
        Assertions.assertThat(clienteGuardado).isNotNull();
        Assertions.assertThat(clienteGuardado.getId()).isNotNull();
    }

    // Pruebas Read ----------------------------------------------------------------------------------------------------
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
        String nombre = "AR";

        // Act
        List<Cliente> clientes = clienteRepository.findByNombreContaining(nombre);

        // Assert
        Assertions.assertThat(clientes).isNotNull();
        // Debería haber 2 clientes que contengan "AR" en su nombre (Carlos y Dario)
        Assertions.assertThat(clientes.size()).isEqualTo(2);
    }

    @Test
    public void ClienteRepository_findByNombreContaining_EmptyCliente() {
        // Arrange
        String nombre = "Z";

        // Act
        List<Cliente> clientes = clienteRepository.findByNombreContaining(nombre);

        // Assert
        Assertions.assertThat(clientes).isEmpty();
    }

    // Pruebas Update --------------------------------------------------------------------------------------------------
    @Test
    public void ClienteRepository_updateByName_Cliente() {
        // Arrange
        String nombre = "Beto";

        // Act
        List<Cliente> clientes = clienteRepository.findByNombreContaining(nombre);
        Assertions.assertThat(clientes).isNotEmpty();    // Mini-Assert
        Cliente cliente = clientes.get(0);
        cliente.setNombre("Beto actualizado");
        Cliente clienteActualizado = clienteRepository.save(cliente);

        // Assert
        Assertions.assertThat(clienteActualizado).isNotNull();
        Assertions.assertThat(clienteActualizado.getNombre()).isEqualTo("Beto actualizado");
    }

    // Esta prueba se debe ejecutar por separado porque se necesita el id del cliente
    // Si se ejecuta junto con el resto de pruebas, los ids de los clientes no serán los mismos y fallará
    @Test
    public void ClienteRepository_updateById_Cliente() {
        // Arrange
        Long index = 2L;

        // Act
        Cliente cliente = clienteRepository.findById(index).get();
        cliente.setNombre("Beto actualizado");
        Cliente clienteActualizado = clienteRepository.save(cliente);

        // Assert
        Assertions.assertThat(clienteActualizado).isNotNull();
        Assertions.assertThat(clienteActualizado.getNombre()).isEqualTo("Beto actualizado");
    }

    // Pruebas Delete --------------------------------------------------------------------------------------------------
    @Test
    public void ClienteRepository_deleteById_EmptyCliente() {
        // Arrange
        Long index = 2L;

        // Act
        clienteRepository.deleteById(index);

        // Assert
        Assertions.assertThat(clienteRepository.findById(index)).isEmpty();
    }

}
