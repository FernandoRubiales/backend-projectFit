package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class PagoResponseDTO {

    private Long id;
    private LocalDateTime fechaHoraPago;
    private String metodoAbonado;
    private BigDecimal montoPago;
    private String mpPaymentId;
    private String nombreSocio;
    private String apellidoSocio;
    private String nombrePlan;
}
