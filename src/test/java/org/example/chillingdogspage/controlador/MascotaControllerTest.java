package org.example.chillingdogspage.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chillingdogspage.Controlador.MascotaController;
import org.example.chillingdogspage.Entidad.Mascota;
import org.example.chillingdogspage.Servicio.MascotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MascotaController.class)  // Solo carga el controlador indicado
@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
@ExtendWith(SpringExtension.class)    // Son pruebas de integración, por lo que necesita correr toda la aplicación
public class MascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;    // Simula las peticiones HTTP

    @MockBean   // Crea un Bean falso de MascotaService
    private MascotaService mascotaService;

    @Autowired  // Convierte objetos Java a JSON y viceversa
    ObjectMapper objectMapper;

    /*
    Notación para pruebas:
    ClaseProbando_metodoProbando_resultadoEsperado
     */

    // Pruebas GET -----------------------------------------------------------------------------------------------------
    @Test
    public void MascotaController_mostrarMascotas_MascotaList() throws Exception {
        Mascota mascota1 = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        Mascota mascota2 = new Mascota("Luna", "Dobberman", 3, 10.5f, "Hipotiroidismo",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        List<Mascota> mascotas = List.of(mascota1, mascota2);

        // Cuando se llame al método findAll (de MascotaService), se retornará la lista de mascotas
        when(mascotaService.findAll()).thenReturn(mascotas);

        ResultActions response = mockMvc.perform(get("/mascotas"));

        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value(mascota1.getNombre()))
                .andExpect(jsonPath("$[0].edad").value(mascota1.getEdad()))
                .andExpect(jsonPath("$[1].nombre").value(mascota2.getNombre()))
                .andExpect(jsonPath("$[1].peso").value(mascota2.getPeso()));
    }

    @Test
    public void MascotaController_obtenerMascota_MascotaNotFound() throws Exception {
        // Cuando se llame al método findById (de MascotaService), se retornará la mascota Renato
        when(mascotaService.findById(Mockito.anyLong())).thenReturn(null);

        ResultActions response = mockMvc.perform(
                get("/mascotas/").param("id", "-1"));

        response.andExpect(status().isNotFound());
    }

    @Test
    public void MascotaController_obtenerMascota_Mascota() throws Exception {
        Mascota mascota = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );

        // Cuando se llame al método findById (de MascotaService), se retornará la mascota Renato
        when(mascotaService.findById(Mockito.anyLong())).thenReturn(mascota);

        ResultActions response = mockMvc.perform(get("/mascotas/1"));

        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value(mascota.getNombre()))
                .andExpect(jsonPath("$.edad").value(mascota.getEdad()))
                .andExpect(jsonPath("$.peso").value(mascota.getPeso()));
    }

    @Test
    public void MascotaController_mostrarMascotasCliente_MascotaList() throws Exception {
        Mascota mascota1 = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        Mascota mascota2 = new Mascota("Luna", "Dobberman", 3, 10.5f, "Hipotiroidismo",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        List<Mascota> mascotas = List.of(mascota1, mascota2);

        // Cuando se llame al método findAllByClienteCedula (de MascotaService), se retornará la lista de mascotas
        when(mascotaService.findAllByClienteCedula(Mockito.anyString())).thenReturn(mascotas);

        ResultActions response = mockMvc.perform(get("/mascotas/cliente/1234567890"));

        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value(mascota1.getNombre()))
                .andExpect(jsonPath("$[0].edad").value(mascota1.getEdad()))
                .andExpect(jsonPath("$[1].nombre").value(mascota2.getNombre()))
                .andExpect(jsonPath("$[1].peso").value(mascota2.getPeso()));
    }

    @Test
    public void MascotaController_mostrarMascotasPorNombre_MascotaList() throws Exception {
        Mascota mascota1 = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        Mascota mascota2 = new Mascota("Luna", "Dobberman", 3, 10.5f, "Hipotiroidismo",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );
        List<Mascota> mascotas = List.of(mascota1, mascota2);

        // Cuando se llame al método findBySimilarName (de MascotaService), se retornará la lista de mascotas
        when(mascotaService.findBySimilarName(Mockito.anyString())).thenReturn(mascotas);

        ResultActions response = mockMvc.perform(get("/mascotas/nombre/na"));

        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value(mascota1.getNombre()))
                .andExpect(jsonPath("$[0].edad").value(mascota1.getEdad()))
                .andExpect(jsonPath("$[1].nombre").value(mascota2.getNombre()))
                .andExpect(jsonPath("$[1].peso").value(mascota2.getPeso()));
    }

    // Pruebas POST ----------------------------------------------------------------------------------------------------
    @Test
    public void MascotaController_registrarMascota_Mascota() throws Exception {
        Mascota mascota = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );

        // Cuando se llame al método registrarMascota (de MascotaService), se retornará la mascota Renato
        when(mascotaService.registrarMascota(Mockito.any(Mascota.class))).thenReturn(mascota);

        ResultActions response = mockMvc.perform(post("/mascotas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mascota)));

        response.andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value(mascota.getNombre()))
                .andExpect(jsonPath("$.edad").value(mascota.getEdad()))
                .andExpect(jsonPath("$.peso").value(mascota.getPeso()));
    }

    // Pruebas PUT -----------------------------------------------------------------------------------------------------
    @Test
    public void MascotaController_actualizarMascota_Mascota() throws Exception {
        Mascota mascota = new Mascota("Renato", "Shih tzu", 5, 15.5f, "Fiebre",
                "https://th.bing.com/th/id/OIP.75N3OkCeQATDyQvQ9RXS9AAAAA?rs=1&pid=ImgDetMain",
                "Activo", null
        );

        // Cuando se llame al método updateMascota (de MascotaService), se retornará la mascota Renato
        when(mascotaService.updateMascota(Mockito.any(Mascota.class))).thenReturn(mascota);

        ResultActions response = mockMvc.perform(put("/mascotas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mascota)));

        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value(mascota.getNombre()))
                .andExpect(jsonPath("$.edad").value(mascota.getEdad()))
                .andExpect(jsonPath("$.peso").value(mascota.getPeso()));
    }

    // Pruebas DELETE --------------------------------------------------------------------------------------------------
    @Test
    public void MascotaController_eliminarMascota_Mascota() throws Exception {
        // Cuando se llame al método deleteMascota (de MascotaService), se retornará true (se eliminó la mascota)
        when(mascotaService.deleteMascota(Mockito.anyLong())).thenReturn(true);

        ResultActions response = mockMvc.perform(delete("/mascotas/1"));

        response.andExpect(status().isNoContent());
    }

    @Test
    public void MascotaController_eliminarMascota_MascotaNotFound() throws Exception {
        // Cuando se llame al método deleteMascota (de MascotaService), se retornará false (no se eliminó la mascota)
        when(mascotaService.deleteMascota(Mockito.anyLong())).thenReturn(false);

        ResultActions response = mockMvc.perform(delete("/mascotas/").param("id", "-1"));

        response.andExpect(status().isNotFound());
    }
}
