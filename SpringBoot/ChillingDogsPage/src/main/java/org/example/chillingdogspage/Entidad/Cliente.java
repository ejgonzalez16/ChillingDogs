package org.example.chillingdogspage.Entidad;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class Cliente {
    private String cedula;
    private String nombre;
    private String correo;
    private String celular;
    private String foto;
    private List<Mascota> mascotas;

    public Cliente(String cedula, String nombre, String correo, String celular, String foto, List<Mascota> mascotas) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.foto = foto;
        this.mascotas = mascotas;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Mascota> mostrarMascotasTratamiento() throws ExecutionControl.NotImplementedException {
        // TODO
        throw new ExecutionControl.NotImplementedException("No implementado :D");
    }
}
