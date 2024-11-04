package org.example.chillingdogspage.repositorio;

import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Administrador;
import org.example.chillingdogspage.Repositorio.AdministradorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest    // Hace que se cargue una DB (H2) en memoria para las pruebas
@ExtendWith(SpringExtension.class)     // Necesario para que se ejecuten las pruebas
public class AdministradorRepositoryTest {

    @Autowired 
    private AdministradorRepository administradorRepository;

    /*@Test
    public void AdministradorRepository_findByCedulaAndContrasena_Administrador() {
        // Arrange
        Administrador administrador = new Administrador("1234567890", "Admin", "1234", "https://th.bing.com/th/id/OIP.uwAx4p0CUpIOYFlSpdsZ4QHaFj?rs=1&pid=ImgDetMain");
        // Act
        Administrador administradorGuardado = administradorRepository.save(administrador);
        Administrador administradorEncontrado = administradorRepository.findByCedulaAndContrasena(administrador.getCedula(), administrador.getContrasena());
        // Assert
        Assertions.assertThat(administradorEncontrado).isNotNull();
        Assertions.assertThat(administradorEncontrado.getCedula()).isEqualTo(administrador.getCedula());
        Assertions.assertThat(administradorEncontrado.getContrasena()).isEqualTo(administrador.getContrasena());
    }*/
    
}
