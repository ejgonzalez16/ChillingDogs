package org.example.chillingdogspage.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data   // Getters y setters
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @GeneratedValue
    @Id
    private Long id;
    private String username;
    private String password;

    // EAGER: Carga todos los roles al cargar el usuario, cascade: Si se elimina el usuario, se eliminan los registros en la tabla intermedia
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // Tabla intermedia para la relación muchos a muchos (un usuario puede tener varios roles y un rol puede asignarse a varios usuarios)
    @JoinTable(
            name = "rol_usuario",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private List<Rol> roles = new ArrayList<>();

    public void addRol(Rol rolDB) {
        roles.add(rolDB);
    }
}
