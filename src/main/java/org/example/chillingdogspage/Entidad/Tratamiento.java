package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
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

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Tratamiento(LocalDate fecha, Mascota mascota, Droga droga, Veterinario veterinario) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.droga = droga;
        this.veterinario = veterinario;
    }
}
