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
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fecha y hora obligatoria")
    private LocalDateTime fechaHoraIngreso;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SedeAsistida")
    private Sede sede;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClaseAsistida")
    private Clase clase;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Reserva")
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SocioPlan")
    private SocioPlan socioPlan;

}
