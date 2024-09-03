package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Droga {
    @GeneratedValue
    @Id
    private Long id;
    private String nombre;
    private Double precioCompra;
    private Double precioVenta;
    private Integer unidadesDisponibles;
    private Integer unidadesVendidas;
    @OneToMany(mappedBy = "droga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tratamiento> tratamientos = new ArrayList<>();
}
