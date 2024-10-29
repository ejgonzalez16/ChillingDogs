package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Tratamiento {
    @GeneratedValue
    @Id
    private Long id;
    private LocalDate fecha;
    @ManyToOne()
    private Mascota mascota;
    @ManyToOne()
    private Droga droga;
    @ManyToOne()
    private Veterinario veterinario;

    public Tratamiento() {
    }

    public Tratamiento(LocalDate fecha, Mascota mascota, Droga droga, Veterinario veterinario) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.droga = droga;
        this.veterinario = veterinario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Droga getDroga() {
        return droga;
    }

    public void setDroga(Droga droga) {
        this.droga = droga;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
