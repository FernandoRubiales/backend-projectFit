package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SedeRequestDTO {

    @NotBlank(message = "Nombre obligatorio")
    private String nombreSede;

    @NotBlank(message = "Dirección obligatoria")
    private String direccion;

    @NotNull(message = "Teléfono obligatorio")
    private Integer telefono;
}
