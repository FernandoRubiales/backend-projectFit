package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SedeDetalleResponseDTO {

    private Long id;
    private String nombreSede;
    private String direccion;
    private Integer telefono;
    private List<SedePlanResponseDTO> planes;
}
