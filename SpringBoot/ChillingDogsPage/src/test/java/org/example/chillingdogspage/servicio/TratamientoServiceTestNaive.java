package org.example.chillingdogspage.servicio;

import org.example.chillingdogspage.Servicio.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

// Implica un mayor tiempo en cada prueba
@SpringBootTest // Crea un contexto de Spring (instancia de la aplicación) para las pruebas
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Reinicia el contexto de Spring antes de cada prueba
@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
public class TratamientoServiceTestNaive {
    @Autowired
    private TratamientoService tratamientoService;
}
