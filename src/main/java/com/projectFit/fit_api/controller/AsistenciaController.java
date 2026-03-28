package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.AsistenciaResponseDTO;
import com.projectFit.fit_api.services.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    //EL SOCIO INGRESA AL COMPLEJO
    @PostMapping
    public ResponseEntity<AsistenciaResponseDTO> registrarAsistencia(String qrCode){
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaService.registrarAsistencia(qrCode));
    }

}
