package org.example.chillingdogspage.Entidad;

public class MascotaCliente{
    private String cedulaDueno;
    private String nombreDueno;
    private Mascota mascota;

    public MascotaCliente(String idDueno, String nombreDueno, Mascota mascota) {
        this.cedulaDueno = idDueno;
        this.nombreDueno = nombreDueno;
        this.mascota = mascota;
    }

    public String getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(String cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
