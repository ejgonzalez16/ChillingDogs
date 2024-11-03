package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
public class Veterinario {
    @GeneratedValue
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Usuario usuario;

    private String cedula;
    @Transient  // No se mapea en la base de datos (ya se está mapeando en Usuario)
    private String contrasena;
    private String especialidad;
    private String nombre;
    private String estado;
    private String foto;
    @JsonIgnore
    // Si se borra un veterinario NO se deben borrar los tratamientos asociados a ese veterinario
    @OneToMany(mappedBy = "veterinario", orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Veterinario(String cedula, String contrasena, String especialidad, String nombre, String estado, String foto) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.especialidad = especialidad;
        this.nombre = nombre;
        this.estado = estado;
        this.foto = foto;
    }
}
