package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "precio obligatorio")
    private BigDecimal precio;
    @NotBlank(message = "dias obligatorio")
    private int diasPorSemana;

    private int clasesIncluidas;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoActividad")
    private TipoActividad tipoActividad;


}
