package org.example.chillingdogspage.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data   // Genera los getters y setters para todos los atributos
// Sirve para moldear la estructura por la cual se enviarán los datos para crear o actualizar un tratamiento
public class TratamientoDTO {
    private Long id;
    private Long mascotaId;
    private Long drogaId;
    private Long veterinarioId;
}
