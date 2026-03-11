package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nombre obligatorio")
    private String nombre;
    @NotBlank(message = "apellido obligatorio")
    private String apellido;
    @NotBlank(message = "dni obligatorio")
    private BigDecimal dni;
    @NotBlank(message = "email obligatorio")
    private String email;
    @NotBlank(message = "telefono obligatorio")
    private String telefono;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Plan")
    private SocioPlan socioPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Estado")
    private EstadoSocio estadoSocio;

}
