package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "direccion obligatoria")
    @Column(nullable = false)
    private String direccion;

    @NotBlank(message = "nombre obligatorio")
    @Column(nullable = false)
    private String nombreSede;

    @NotNull(message = "telefono obligatorio")
    @Column(nullable = false)
    private Integer telefono;

    private LocalDateTime fechaHoraBajaSede;

    //RELACIONES
    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
    private List<SedePlan> sedePlanes;
}
