package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    // Si se borra una droga NO se deben borrar los tratamientos asociados a esa droga
    @OneToMany(mappedBy = "droga", orphanRemoval = false)
    private List<Tratamiento> tratamientos = new ArrayList<>();

    public Droga() {
    }

    public Droga(String nombre, Double precioVenta, Double precioCompra, Integer unidadesDisponibles) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
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

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
