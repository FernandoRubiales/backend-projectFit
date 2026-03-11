package com.projectFit.fit_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "fecha y hora obligatoria")
    private LocalDateTime fechaHoraPago;
    @NotBlank(message = "metodo obligatorio")
    private String metodoAbonado;
    @NotBlank(message = "monto obligatorio")
    private BigDecimal montoPago;

    //RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SocioPago")
    private SocioPlan socioPlan;

}
