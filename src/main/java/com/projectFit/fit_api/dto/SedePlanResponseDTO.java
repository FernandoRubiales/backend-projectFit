package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SedePlanResponseDTO {

    private Long id;
    private String nombreSede;
    private String nombrePlan;
    private String tipoActividad;
}
