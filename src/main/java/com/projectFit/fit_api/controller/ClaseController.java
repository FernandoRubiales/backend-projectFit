package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.ClaseRequestDTO;
import com.projectFit.fit_api.dto.ClaseResponseDTO;
import com.projectFit.fit_api.services.ClaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clases")
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService claseService;

    //CREATE CLASE
    @PostMapping
    public ResponseEntity<ClaseResponseDTO> crearClase(@Valid @RequestBody ClaseRequestDTO claseRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(claseService.crearClase(claseRequestDTO));
    }
}
