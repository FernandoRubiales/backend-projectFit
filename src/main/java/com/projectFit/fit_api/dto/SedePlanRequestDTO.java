package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SedePlanRequestDTO {

    @NotNull(message = "Sede obligatoria")
    private Long sedeId;

    @NotNull(message = "Plan obligatorio")
    private Long planId;
}
