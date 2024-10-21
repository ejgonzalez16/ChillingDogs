package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
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
public class DrogaRepositoryTest {
    
    @Autowired
    private DrogaRepository drogaRepository;

    @Test
    public void DrogaRepository_findDisponibles_Drogas() {
        //Arrange
        Droga droga1 = new Droga("Droga 1", 15.0, 10.0, 5);
        Droga droga2 = new Droga("Droga 2", 25.0, 20.0, 10);
        Droga droga3 = new Droga("Droga 3", 40.0, 30.0, 0);

        drogaRepository.save(droga1);
        drogaRepository.save(droga2);
        drogaRepository.save(droga3);

        //Act
        List<Droga> drogas = drogaRepository.findDisponibles();

        //Assert
        Assertions.assertThat(drogas).isNotNull();
        Assertions.assertThat(drogas).contains(droga1, droga2);
    }
}
