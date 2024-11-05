package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jshell.spi.ExecutionControl;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
public class Cliente {
    @GeneratedValue
    @Id
    private Long id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})   // Al eliminar un cliente no intente automáticamente eliminar su usuario asociado.
    @JsonIgnore
    private Usuario usuario;

    private String cedula;
    private String nombre;
    private String correo;
    private String celular;
    private String foto;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Cliente(String cedula, String nombre, String correo, String celular, String foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.foto = foto;
    }
}
