package org.example.chillingdogspage.ErrorHandling;

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
