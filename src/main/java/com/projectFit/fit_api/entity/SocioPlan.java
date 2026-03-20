package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private int clasesDisponibles;

    private LocalDateTime fechaInicioSocioPlan;
    private LocalDateTime fechaVencimientoSocioPlan;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Estado_id")
    private EstadoSocioPlan estadoSocioPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Socio_id")
    private Socio socio;


}
