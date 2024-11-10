package org.example.chillingdogspage.Entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // Genera los getters y setters para todos los atributos
@Builder    // Builder permite crear objetos de forma más clara sin usar un constructor
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor
public class Email {
    private String nombre;
    private String apellido;
    private String pelidutoName;
    private String correo;
    private String fecha;
}
