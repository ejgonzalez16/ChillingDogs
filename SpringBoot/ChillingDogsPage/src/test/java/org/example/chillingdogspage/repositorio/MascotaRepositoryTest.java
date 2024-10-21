package org.example.chillingdogspage.repositorio;

import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@RunWith(SpringRunner.class)    // Necesario para que se ejecuten las pruebas
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

        mascotaRepository.save(new Mascota("Max", "Pastor aleman", 5, 15.5, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente1));
        mascotaRepository.save(new Mascota("Pepe", "Samoyedo", 5, 15.5, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente2));
        mascotaRepository.save(new Mascota("Bengie", "Shih tzu", 5, 15.5, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente1));
        mascotaRepository.save(new Mascota("Tony", "Pitbull", 5, 15.5, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente2));
        mascotaRepository.save(new Mascota("Max", "Golden retriever", 5, 15.5, "Fiebre", "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain", "Activo", cliente3));
    }

    @Test
    public void MascotaRepository_findByNombreContaining_Mascotas() {
        List<Mascota> mascotas = mascotaRepository.findByNombreContaining("Perro");
        Assertions.assertThat(mascotas).isNotEmpty();
    }

    
}
