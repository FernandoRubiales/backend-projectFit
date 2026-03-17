package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SedeResponseDTO {

    private Long id;
    private String nombreSede;
    private String direccion;
    private Integer telefono;
}
