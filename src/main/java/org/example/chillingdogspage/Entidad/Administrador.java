package org.example.chillingdogspage.Entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Administrador {
    @GeneratedValue
    @Id
    private Long id;
    private String cedula;
    private String nombre;
    private String contrasena;
    private String foto;

    public Administrador() {
    }

    public Administrador(String cedula, String nombre, String contrasena, String foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
