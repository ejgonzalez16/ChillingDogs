package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicamentosMes {
    @JsonProperty("medicamento") // (Opcional) Agrega anotaciones para personalizar la serialización
    private String medicamento;

    @JsonProperty("cantidad") // (Opcional)
    private Long cantidad;

    // Constructor
    public MedicamentosMes(String medicamento, Long cantidad) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    // Getters
    public String getMedicamento() {
        return medicamento;
    }

    public Long getCantidad() {
        return cantidad;
    }

    // (Opcional) Setters, si necesitas
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
