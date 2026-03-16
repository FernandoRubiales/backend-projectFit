package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanResponseDTO {

    private Long id;
    private String nombrePlan;
    private String descripcion;
    private BigDecimal precio;
    private int diasPorSemana;
    private int clasesIncluidas;
    private String tipoActividad;
}
