package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinario {
    @GeneratedValue
    @Id
    private Long id;
    private String cedula;
    private String contrasena;
    private String especialidad;
    private int numeroAtenciones;
    private String nombre;
    private String estado;
    private String foto;
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tratamiento> tratamientos = new ArrayList<>();
}
