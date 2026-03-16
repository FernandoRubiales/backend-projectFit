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
public class TipoActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "descripcion obligatoria")
    private String descripcion;

    @NotBlank(message = "nombre obligatorio")
    private String nombreTipoActividad;

    @Column(nullable = false)
    private Boolean requiereReserva;

    private LocalDateTime fechaHoraBajaTA;
}
