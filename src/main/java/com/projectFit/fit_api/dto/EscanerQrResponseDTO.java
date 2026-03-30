package com.projectFit.fit_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscanerQrResponseDTO {

    private String nombreSocio;
    private String apellidoSocio;
    private String nombreTipoActividad;
    private int clasesRestante;
    private String mensaje;
}
