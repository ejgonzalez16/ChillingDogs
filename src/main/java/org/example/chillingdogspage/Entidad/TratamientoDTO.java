package org.example.chillingdogspage.Entidad;

// Sirve para moldear la estructura por la cual se enviarán los datos para crear o actualizar un tratamiento
public class TratamientoDTO {
    private Long id;
    private Long mascotaId;
    private Long drogaId;
    private Long veterinarioId;

    public TratamientoDTO() {
    }

    public TratamientoDTO(Long id, Long mascotaId, Long drogaId, Long veterinarioId) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.drogaId = drogaId;
        this.veterinarioId = veterinarioId;
    }

    public Long getId() {
        return id;
    }

    public Long getMascotaId() {
        return mascotaId;
    }

    public Long getDrogaId() {
        return drogaId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }
}
