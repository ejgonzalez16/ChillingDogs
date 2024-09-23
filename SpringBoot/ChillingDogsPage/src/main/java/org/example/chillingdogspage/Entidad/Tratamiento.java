package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Tratamiento {
    @GeneratedValue
    @Id
    private Long id;
    private LocalDateTime fecha;
    @ManyToOne()
    private Mascota mascota;
    @ManyToOne()
    private Droga droga;
    @ManyToOne()
    private Veterinario veterinario;
}
