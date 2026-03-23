package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ReservaResponseDTO {

    private Long id;
    private LocalDateTime fechaHoraReserva;
    private String nombreSocio;
    private String apellidoSocio;
    private String tipoActividad;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String nombreSede;
}
