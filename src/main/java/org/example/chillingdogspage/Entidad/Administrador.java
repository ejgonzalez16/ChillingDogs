package org.example.chillingdogspage.Entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   // Genera los getters y setters para todos los atributos
@NoArgsConstructor  // Genera un constructor vacío
public class Administrador {
    @GeneratedValue
    @Id
    private Long id;
    private String cedula;
    private String nombre;
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
