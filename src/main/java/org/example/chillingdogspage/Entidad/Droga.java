package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@Builder    // Builder permite crear objetos de forma más clara sin usar un constructor
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
public class Droga {
    @GeneratedValue
    @Id
    private Long id;
    private String nombre;
    private Double precioCompra;
    private Double precioVenta;
    private Integer unidadesDisponibles;
    @Transient
    private Long unidadesVendidas;
    @JsonIgnore
    // Si se borra una droga NO se deben borrar los tratamientos asociados a esa droga
    @OneToMany(mappedBy = "droga", orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Droga(String nombre, Double precioVenta, Double precioCompra, Integer unidadesDisponibles) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    // Método para usar una droga (reduce las unidades disponibles en 1)
    public void usarDroga() {
        this.unidadesDisponibles--;
    }

    // Método para devolver una droga (aumenta las unidades disponibles en 1)
    public void devolverDroga() {
        this.unidadesDisponibles++;
    }
}
