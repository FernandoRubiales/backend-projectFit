package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.EscanerQrRequestDTO;
import com.projectFit.fit_api.dto.EscanerQrResponseDTO;
import com.projectFit.fit_api.services.QrService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qr")
@RequiredArgsConstructor
public class QrController {

    private final QrService qrService;

    //Procesar el escaneo del Qr una vez que ingresa al predio
    @PostMapping("/scan")
    public ResponseEntity<EscanerQrResponseDTO> escanearQr(@Valid @RequestBody EscanerQrRequestDTO escanerQrRequestDTO){
        return ResponseEntity.ok(qrService.escanearQr(escanerQrRequestDTO));
    }

}
