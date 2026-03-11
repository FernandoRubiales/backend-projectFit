package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "dia de semana obligatoria")
    private String diaSemana;
    @NotBlank(message = "fecha y hora de inicio obligatoria")
    private LocalDateTime fechaHoraInicio;
    @NotBlank(message = "fecha y hora de fin obligatoria")
    private LocalDateTime fechaHoraFin;
    @NotBlank(message = "cupo maximo por clase obligatorio")
    private int cupoMaximo;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Sede")
    private Sede sede;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoActividad")
    private TipoActividad tipoActividad;


}
