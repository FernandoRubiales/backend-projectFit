package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.SedePlanRequestDTO;
import com.projectFit.fit_api.dto.SedePlanResponseDTO;
import com.projectFit.fit_api.services.SedePlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sede-plan")
@RequiredArgsConstructor
public class SedePlanController {

    private final SedePlanService sedePlanService;

    //ASOCIAR PLAN A UNA SEDE
    @PostMapping
    public ResponseEntity<SedePlanResponseDTO> agregar(
            @Valid @RequestBody SedePlanRequestDTO sedePlanRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sedePlanService.agregar(sedePlanRequestDTO));
    }

    //GET PLANES EN UNA SEDE
    @GetMapping("/sede/{sedeId}")
    public ResponseEntity<List<SedePlanResponseDTO>> obtenerPorSede(
            @PathVariable Long sedeId) {
        return ResponseEntity.ok(sedePlanService.obtenerPorSede(sedeId));
    }

    //DAR DE BAJA UN PLAN DE UNA SEDE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBaja(@PathVariable Long id) {
        sedePlanService.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}


