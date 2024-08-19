package org.example.chillingdogspage.Controlador;

public class ClientNotFoundException extends RuntimeException{
    private String cedula;

    public ClientNotFoundException(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
