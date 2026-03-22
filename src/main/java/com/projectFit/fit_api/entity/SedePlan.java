package com.projectFit.fit_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class SedePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Para saber desde cuándo está disponible ese plan en esa sede
    @Column(nullable = false)
    private LocalDateTime fechaAltaSedePlan;

    private LocalDateTime fechaBajaSedePlan;

    //RELACIONES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sede_id", nullable = false)
    private Sede sede;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Plan_id", nullable = false)
    private Plan plan;

}
