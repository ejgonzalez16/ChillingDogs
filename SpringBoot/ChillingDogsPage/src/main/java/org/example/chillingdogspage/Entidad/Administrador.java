package org.example.chillingdogspage.Entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Administrador {
    @GeneratedValue
    @Id
    private Long id;
    private String cedula;
    private String contrasena;
    private String foto;
}
