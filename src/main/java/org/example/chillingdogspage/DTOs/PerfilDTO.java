package org.example.chillingdogspage.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PerfilDTO {
    private String nombre;
    private String foto;
}
