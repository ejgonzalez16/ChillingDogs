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
public class Mascota {
    @GeneratedValue
    @Id
    private Long id;
    private String nombre;
    private String raza;
    private int edad;
    private float peso;
    private String enfermedad;
    private String foto;
    private String estado;
    @ManyToOne()
    private Cliente cliente;
    @JsonIgnore
    // Si se borra una mascota NO se deben borrar los tratamientos asociados a esa mascota
    @OneToMany(mappedBy = "mascota", orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Mascota(String nombre, String raza, int edad, float peso, String enfermedad, String foto, String estado, Cliente cliente) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.enfermedad = enfermedad;
        this.foto = foto;
        this.estado = estado;
        this.cliente = cliente;
    }
}
