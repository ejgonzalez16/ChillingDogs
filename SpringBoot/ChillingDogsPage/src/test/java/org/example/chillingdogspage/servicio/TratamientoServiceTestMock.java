package org.example.chillingdogspage.servicio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.*;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Repositorio.TratamientoRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.example.chillingdogspage.Servicio.TratamientoService;
import org.example.chillingdogspage.Servicio.TratamientoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test") // Cada que se ejecute una prueba de esta clase, usa las configuraciones del perfil test application-test.properties
@ExtendWith(MockitoExtension.class)     // Habilita el uso de Mockito en la prueba
public class TratamientoServiceTestMock {
    @InjectMocks   // Inyecta los mocks en la clase que se va a probar (la implementación, no la interfaz)
    private TratamientoServiceImpl tratamientoService;

    @Mock   // Crea un Mock (objeto falso para realizar acciones falsas) de TratamientoRepository
    private TratamientoRepository tratamientoRepository;

    @Mock
    private DrogaRepository drogaRepository;

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    // Create ----------------------------------------------------------------------------------------------------------
    @Test
    public void TratamientoService_registrarTratamiento_Tratamiento(){
        //arrange
        Droga droga = new Droga("Droga", 500.0, 200.0, 5);
        Veterinario veterinario = new Veterinario("1", "contra", "especial", "Ricaurte", "Activo", "Foto");
        TratamientoDTO tratamientoDTO = new TratamientoDTO();
        Cliente cliente = new Cliente();
        Mascota mascota = new Mascota("Silva", "Transformer", 15000, 850.0f, "Ser silva", "", "Inactivo", cliente);
        Tratamiento tratamiento = new Tratamiento(LocalDate.now(), mascota, droga, veterinario);
        when(drogaRepository.findById(tratamientoDTO.getDrogaId())).thenReturn(Optional.of(droga));
        when(veterinarioRepository.findById(tratamientoDTO.getVeterinarioId())).thenReturn(Optional.of(veterinario));
        when(mascotaRepository.findById(tratamientoDTO.getMascotaId())).thenReturn(Optional.of(mascota));
        when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenReturn(tratamiento);

        //act
        Tratamiento newTratamiento = tratamientoService.registrarTratamiento(tratamientoDTO);

        //assert
        Assertions.assertThat(newTratamiento).isNotNull();
    }

    // Read ------------------------------------------------------------------------------------------------------------
    @Test
    public void TratamientoService_findAll_TratamientoList(){
        //arrange
        when(tratamientoRepository.findAll()).thenReturn(List.of(new Tratamiento(), new Tratamiento()));

        //act
        List<Tratamiento> tratamientos = tratamientoService.findAll();

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_findById_Tratamiento(){
        //arrange
        when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(new Tratamiento()));

        //act
        Tratamiento tratamiento = tratamientoService.findById(1L);

        //assert
        Assertions.assertThat(tratamiento).isNotNull();
    }

    @Test
    public void TratamientoService_findAllByMascotaId_TratamientoList(){
        //arrange
        when(tratamientoRepository.findAllByMascotaId(1L)).thenReturn(List.of(new Tratamiento()));

        //act
        List<Tratamiento> tratamientos = tratamientoService.findAllByMascotaId(1L);

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(1);
    }

    @Test
    public void TratamientoService_findAllByVeteranioId_TratamientoList(){
        //arrange
        when(tratamientoRepository.findAllByVeterinarioId(1L)).thenReturn(List.of(new Tratamiento(), new Tratamiento(), new Tratamiento()));

        //act
        List<Tratamiento> tratamientos = tratamientoService.findAllByVeterinarioId(1L);

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(3);
    }

    /*@Test
    public void TratamientoService_updateTratamiento_Tratamiento(){
        //arrange
        Droga droga = new Droga("Droga", 500.0, 200.0, 5);
        Veterinario veterinario = new Veterinario("1", "contra", "especial", "Ricaurte", "Activo", "Foto");
        TratamientoDTO tratamientoDTO = new TratamientoDTO(1L, 2L, 3L, 4L);
        Cliente cliente = new Cliente();
        Mascota mascota = new Mascota("Silva", "Transformer", 15000, 850.0f, "Ser silva", "", "Inactivo", cliente);
        Tratamiento tratamiento = new Tratamiento(LocalDate.now(), mascota, droga, veterinario);
        when(tratamientoRepository.findById(tratamientoDTO.getId())).thenReturn(Optional.of(tratamiento));
        when(drogaRepository.findById(tratamientoDTO.getDrogaId())).thenReturn(Optional.of(droga));
        when(veterinarioRepository.findById(tratamientoDTO.getVeterinarioId())).thenReturn(Optional.of(veterinario));
        when(mascotaRepository.findById(tratamientoDTO.getMascotaId())).thenReturn(Optional.of(mascota));
        when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenReturn(tratamiento);

        //act
        Tratamiento newTratamiento = tratamientoService.updateTratamiento(tratamientoDTO);

        //assert
        Assertions.assertThat(newTratamiento).isNotNull();
    }

    @Test
    public void TratamientoService_deleteTratamiento_Boolean(){
        //arrange
        Tratamiento tratamiento = new Tratamiento();
        when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));

        //act
        boolean realizado = tratamientoService.deleteTratamiento(1L);

        //assert
        Assertions.assertThat(realizado).isTrue();
    }*/
}
