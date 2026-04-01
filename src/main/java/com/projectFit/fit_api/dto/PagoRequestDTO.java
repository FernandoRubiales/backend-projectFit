package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagoRequestDTO {

    @NotNull(message = "Monto obligatorio")
    private BigDecimal montoPago;

    @NotNull(message = "SocioPlan obligatorio")
    private Long socioPlanId;
}
