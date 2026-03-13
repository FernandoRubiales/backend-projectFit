package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "descripcion obligatoria")
    private String descripcion;

    @NotNull(message = "precio obligatorio")
    private BigDecimal precio;

    @NotNull(message = "dias obligatorio")
    private int diasPorSemana;

    @Column(nullable = false)
    private int clasesIncluidas;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoActividad_id")
    private TipoActividad tipoActividad;


}
