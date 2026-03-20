package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocioPlanRequestDTO {

    @NotNull(message = "Socio obligatorio")
    private Long socioId;

    @NotNull(message = "Plan obligatorio")
    private Long planId;

}
