package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Droga;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
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
        Droga droga1 = Droga.builder().nombre("Droga 1").precioCompra(10.0).precioVenta(15.0).unidadesDisponibles(5).build();
        Droga droga2 = Droga.builder().nombre("Droga 2").precioCompra(20.0).precioVenta(25.0).unidadesDisponibles(10).build();
        Droga droga3 = Droga.builder().nombre("Droga 3").precioCompra(30.0).precioVenta(40.0).unidadesDisponibles(0).build();

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
