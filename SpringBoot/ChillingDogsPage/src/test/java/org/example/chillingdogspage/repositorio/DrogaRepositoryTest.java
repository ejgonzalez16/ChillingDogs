package org.example.chillingdogspage.repositorio;

import org.example.chillingdogspage.Entidad.Droga;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@RunWith(SpringRunner.class)    // Necesario para que se ejecuten las pruebas
public class DrogaRepositoryTest {
    
    @Autowired
    private DrogaRepository drogaRepository;

    @Test
    public void DrogaRepository_findDisponibles_Drogas() {
        //Arrange
        Droga droga1 = new Droga("Droga 1", "Descripción 1", 10.0, 5);
        Droga droga2 = new Droga("Droga 2", "Descripción 2", 20.0, 10);
        Droga droga3 = new Droga("Droga 3", "Descripción 3", 30.0, 0);

        //Act
        List<Droga> drogas = drogaRepository.findDisponibles();

        //Assert
        Assertions.assertThat(drogas).isNotEmpty();
        Assertions.assertThat(drogas).contains(droga1, droga2);
    }
}
