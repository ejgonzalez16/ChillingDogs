package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
public class Administrador {
    @GeneratedValue
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Usuario usuario;

    private String cedula;
    private String nombre;
    @Transient  // No se mapea en la base de datos (ya se está mapeando en Usuario)
    private String contrasena;
    private String foto;

    // Importante tener un constructor vacío y un constructor sin id ni relaciones
    public Administrador(String cedula, String nombre, String contrasena, String foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = foto;
    }
}
