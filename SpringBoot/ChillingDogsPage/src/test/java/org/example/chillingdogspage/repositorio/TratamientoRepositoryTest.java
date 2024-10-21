package org.example.chillingdogspage.repositorio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@ExtendWith(SpringExtension.class)     // Necesario para que se ejecuten las pruebas
public class TratamientoRepositoryTest {

    @Test
    public void TratamientoRepository_countTratamiento_cantidad() {

    }

}