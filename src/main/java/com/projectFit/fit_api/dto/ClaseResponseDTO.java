package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
public class ClaseResponseDTO {
    private Long id;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int cupoMaximo;
    private int cuposDisponibles;
    private String nombreSede;
    private String nombreTipoActividad;
}
