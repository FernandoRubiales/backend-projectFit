package com.projectFit.fit_api.controller;

import com.projectFit.fit_api.dto.SedeRequestDTO;
import com.projectFit.fit_api.dto.SedeResponseDTO;
import com.projectFit.fit_api.services.SedeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedes")
@RequiredArgsConstructor
public class SedeController {

    private final SedeService sedeService;

    //CREATE SEDE
    @PostMapping
    public ResponseEntity<SedeResponseDTO> crearSede(@Valid @RequestBody SedeRequestDTO sedeRequestDTO){
        SedeResponseDTO sedeResponse = sedeService.crearSede(sedeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sedeResponse);
    }

    //UPDATE SEDE
    @PutMapping("/{id}")
    public ResponseEntity<SedeResponseDTO> actualizarSede(@PathVariable Long id, @Valid @RequestBody SedeRequestDTO sedeRequestDTO){
        SedeResponseDTO sedeResponse = sedeService.actualizarSede(id, sedeRequestDTO);
        return ResponseEntity.ok(sedeResponse);
    }

    //DELETE SEDE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBajaSede(@PathVariable Long id){
        sedeService.darDeBajaSede(id);
        return ResponseEntity.noContent().build();
    }

    //GET SEDE por ID
    @GetMapping("/{id}")
    public ResponseEntity<SedeResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sedeService.obtenerPorId(id));
    }

    //GET ALL SEDE
    @GetMapping
    public ResponseEntity<List<SedeResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(sedeService.obtenerTodas());
    }
}
