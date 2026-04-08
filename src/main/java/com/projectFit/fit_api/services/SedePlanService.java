package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.SedePlanRequestDTO;
import com.projectFit.fit_api.dto.SedePlanResponseDTO;
import com.projectFit.fit_api.entity.Plan;
import com.projectFit.fit_api.entity.Sede;
import com.projectFit.fit_api.entity.SedePlan;
import com.projectFit.fit_api.mappers.SedePlanMapper;
import com.projectFit.fit_api.repository.PlanRepository;
import com.projectFit.fit_api.repository.SedePlanRepository;
import com.projectFit.fit_api.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SedePlanService {

    private final SedeRepository sedeRepository;
    private final PlanRepository planRepository;
    private final SedePlanMapper sedePlanMapper;
    private final SedePlanRepository sedePlanRepository;

    //ASOCIAR PLAN A UNA SEDE
    public SedePlanResponseDTO agregar(SedePlanRequestDTO sedePlanRequestDTO) {
        Sede sede = sedeRepository.findByIdAndFechaHoraBajaSedeIsNull(sedePlanRequestDTO.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        Plan plan = planRepository.findByIdAndFechaHoraBajaPlanIsNull(sedePlanRequestDTO.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        //Validar que no exista ese plan en esa sede
        if (sedePlanRepository.existePlanEnSede(
                sedePlanRequestDTO.getSedeId(), sedePlanRequestDTO.getPlanId())) {
            throw new RuntimeException(
                    "Ese plan ya está disponible en esa sede");
        }
        SedePlan sedePlan = new SedePlan();
        sedePlan.setSede(sede);
        sedePlan.setPlan(plan);
        sedePlan.setFechaHoraAltaSedePlan(LocalDateTime.now());

        return sedePlanMapper.toResponse(sedePlanRepository.save(sedePlan));
    }

    //GET PLANES EN UNA SEDE
    public List<SedePlanResponseDTO> obtenerPorSede(Long sedeId) {
        return sedePlanRepository.planesDisponiblesEnSede(sedeId)
                .stream()
                .map(sedePlanMapper::toResponse)
                .toList();
    }

    //DAR DE BAJA UN PLAN DE UNA SEDE
    public void darDeBaja(Long id) {
        SedePlan sedePlan = sedePlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SedePlan no encontrado"));
        sedePlan.setFechaHoraBajaSedePlan(LocalDateTime.now());
        sedePlanRepository.save(sedePlan);
    }
}
