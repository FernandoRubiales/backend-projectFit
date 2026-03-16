package com.projectFit.fit_api.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanRequestDTO {

    @NotNull(message = "nombre obligatorio")
    private String nombrePlan;

    @NotNull(message = "descripcion obligatorio")
    private String descripcion;

    @NotNull(message = "precio obligatorio")
    private BigDecimal precio;

    @NotNull(message = "dias obligatorio")
    private int diasPorSemana;

    @NotNull(message = "tipo de actividad obligatoria")
    private Long tipoActividadId;

}
