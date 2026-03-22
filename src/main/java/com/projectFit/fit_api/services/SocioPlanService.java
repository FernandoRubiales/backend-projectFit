package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.SocioPlanRequestDTO;
import com.projectFit.fit_api.dto.SocioPlanResponseDTO;
import com.projectFit.fit_api.entity.EstadoSocioPlan;
import com.projectFit.fit_api.entity.Plan;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.entity.SocioPlan;
import com.projectFit.fit_api.mappers.SocioPlanMapper;
import com.projectFit.fit_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioPlanService {

    private final SocioPlanRepository socioPlanRepository;
    private final SocioRepository socioRepository;
    private final PlanRepository planRepository;
    private final SocioPlanMapper socioPlanMapper;
    private final EstadoSocioPlanRepository estadoSocioPlanRepository;

    //ELEGIR UN PLAN
    public SocioPlanResponseDTO elegirPlan(SocioPlanRequestDTO socioPlanRequestDTO, String auth0Id){
        Socio socio = socioRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        Plan plan = planRepository.findByIdAndFechaHoraBajaPlanIsNull(socioPlanRequestDTO.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        //Comprobar que no tenga ese plan activo
        socioPlanRepository.planActivoporSocioyPlanId(socio.getId(), socioPlanRequestDTO.getPlanId())
                .ifPresent(p -> {
                    throw new RuntimeException("Ya tenés ese plan activo");
                });

        //  Buscar el estado "Pendiente" de un socioPlan
        EstadoSocioPlan estadoSocioPlan = estadoSocioPlanRepository.findByNombreEstadoSocioPlan("Pendiente")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        SocioPlan socioPlan = new SocioPlan();
        socioPlan.setSocio(socio);
        socioPlan.setPlan(plan);
        socioPlan.setClasesDisponibles(0);
        socioPlan.setFechaVencimientoSocioPlan(null);
        socioPlan.setFechaInicioSocioPlan(null);
        socioPlan.setEstadoSocioPlan(estadoSocioPlan);
        SocioPlan socioPlanGuardado = socioPlanRepository.save(socioPlan);

        return socioPlanMapper.toResponse(socioPlanGuardado);
    }

    //CUANDO SE ACEPTE EL PAGO, SE ACTIVA EL PLAN DEL SOCIO
    public SocioPlanResponseDTO activarPlan(Long socioPlanId){
        SocioPlan socioPlan = socioPlanRepository.findById(socioPlanId)
                .orElseThrow(() -> new RuntimeException("SocioPlan no encontrado"));

        //Buscamos el estado Activo de socioPlan
        EstadoSocioPlan estadoSocioPlan = estadoSocioPlanRepository.findByNombreEstadoSocioPlan("Activo")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        socioPlan.setEstadoSocioPlan(estadoSocioPlan);
        socioPlan.setFechaInicioSocioPlan(LocalDateTime.now());
        socioPlan.setFechaVencimientoSocioPlan(LocalDateTime.now().plusMonths(1));
        socioPlan.setClasesDisponibles(socioPlan.getPlan().getClasesIncluidas());
        SocioPlan socioPlanGuardado = socioPlanRepository.save(socioPlan);

        return socioPlanMapper.toResponse(socioPlanGuardado);
    }
    //GET DE LOS PLANES ACTIVOS DEL SOCIO
    public List<SocioPlanResponseDTO> obtenerPlanesActivos(String auth0Id){
        Socio socio = socioRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
        return socioPlanRepository.planesActivosBySocioId(socio.getId())
                .stream()
                .map(socioPlanMapper::toResponse)
                .toList();

    }

}
