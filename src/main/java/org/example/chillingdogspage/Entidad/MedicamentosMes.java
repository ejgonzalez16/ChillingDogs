package org.example.chillingdogspage.Entidad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MedicamentosMes {
    @JsonProperty("medicamento") // (Opcional) Agrega anotaciones para personalizar la serialización
    private String medicamento;

    @JsonProperty("cantidad") // (Opcional)
    private Long cantidad;
}
