package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ClaseRequestDTO {

    @NotBlank(message = "Día de semana obligatorio")
    private String diaSemana;

    @NotNull(message = "Hora inicio obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "Hora fin obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "Cupo máximo obligatorio")
    private int cupoMaximo;

    @NotNull(message = "Sede obligatoria")
    private Long sedeId;

    @NotNull(message = "Tipo de actividad obligatorio")
    private Long tipoActividadId;
}
