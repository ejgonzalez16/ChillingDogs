package org.example.chillingdogspage.servicio;

import org.example.chillingdogspage.Repositorio.TratamientoRepository;
import org.example.chillingdogspage.Servicio.TratamientoService;
import org.example.chillingdogspage.Servicio.TratamientoServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
@ExtendWith(MockitoExtension.class)     // Habilita el uso de Mockito en la prueba
public class TratamientoServiceTestMock {
    @InjectMocks   // Inyecta los mocks en la clase que se va a probar (la implementación, no la interfaz)
    private TratamientoServiceImpl tratamientoService;

    @Mock   // Crea un Mock (objeto falso para realizar acciones falsas) de TratamientoRepository
    private TratamientoRepository tratamientoRepository;
}
