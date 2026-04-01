package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.PlanRequestDTO;
import com.projectFit.fit_api.dto.PlanResponseDTO;
import com.projectFit.fit_api.services.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planes")
public class PlanController {

    private final PlanService planService;

    //CREATE PLAN
    @PostMapping
    public ResponseEntity<PlanResponseDTO> crearPlan(@Valid @RequestBody PlanRequestDTO planRequestDTO){
        PlanResponseDTO planResponse = planService.crearPlan(planRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(planResponse);
    }

    //UPDATE PLAN
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> actualizarPlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDTO planRequestDTO){
        PlanResponseDTO planResponse = planService.actualizarPlan(id, planRequestDTO);
        return ResponseEntity.ok(planResponse);

    }

    //DELETE PLAN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBajaPlan(@PathVariable Long id){
        planService.darDeBajaPlan(id);
        return ResponseEntity.noContent().build();
    }

    //GET PLAN por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(planService.obtenerPorId(id));
    }

    //GET ALL PLAN
    @GetMapping
    public ResponseEntity<List<PlanResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(planService.obtenerTodos());
    }

    //GET ALL PLAN por TipoActividad
    @GetMapping("/actividad/{tipoActividadId}")
    public ResponseEntity<List<PlanResponseDTO>> obtenerPorActividad(@PathVariable Long tipoActividadId){
        return ResponseEntity.ok(planService.obtenerPorActividad(tipoActividadId));
    }
}
