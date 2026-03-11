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
public class SocioPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "clases obligatorio")
    private int clasesDisponibles;
    @NotBlank(message = "fecha inicio obligatoria")
    private LocalDateTime fechaInicio;
    @NotBlank(message = "fecha vencimiento obligatoria")
    private LocalDateTime fechaVencimiento;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Estado")
    private EstadoSocioPlan estadoSocioPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Plan")
    private Plan plan;


}
