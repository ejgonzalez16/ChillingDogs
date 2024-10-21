package org.example.chillingdogspage.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chillingdogspage.Controlador.MascotaController;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MascotaController.class)  // Solo carga el controlador indicado
@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
@RunWith(SpringRunner.class)    // Son pruebas de integración, por lo que necesita correr toda la aplicación
public class MascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean   // Crea un Bean falso de MascotaService
    private MascotaService mascotaService;

    @Autowired  // Convierte objetos Java a JSON y viceversa
    ObjectMapper objectMapper;
}
