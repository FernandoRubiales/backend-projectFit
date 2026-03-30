package com.projectFit.fit_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscanerQrRequestDTO {

    @NotBlank(message = "Qr obligatorio")
    private String qrCode;

    @NotNull(message = "Sede obligatoria")
    private Long sedeId;
}
