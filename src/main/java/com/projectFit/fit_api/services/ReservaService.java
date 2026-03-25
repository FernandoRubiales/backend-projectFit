package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.ReservaRequestDTO;
import com.projectFit.fit_api.dto.ReservaResponseDTO;
import com.projectFit.fit_api.entity.Clase;
import com.projectFit.fit_api.entity.Reserva;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.entity.SocioPlan;
import com.projectFit.fit_api.mappers.ReservaMapper;
import com.projectFit.fit_api.repository.ClaseRepository;
import com.projectFit.fit_api.repository.ReservaRepository;
import com.projectFit.fit_api.repository.SocioPlanRepository;
import com.projectFit.fit_api.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final SocioRepository socioRepository;
    private final ClaseRepository claseRepository;
    private final SocioPlanRepository socioPlanRepository;
    private final ReservaMapper reservaMapper;

    //REALIZAR UNA RESERVA
    public ReservaResponseDTO realizarReserva(ReservaRequestDTO reservaRequestDTO, String auth0Id){
        Socio socio = socioRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        Clase clase = claseRepository.findById(reservaRequestDTO.getClaseId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        SocioPlan socioPlan = socioPlanRepository.planActivoporSocioyActividadId(socio.getId(), clase.getTipoActividad())
                .orElseThrow(() -> new RuntimeException("No tenés un plan activo para esta actividad"));

        //Validar que le queden clases disponibles del plan para reservar
        if(socioPlan.getClasesDisponibles() <= 0){
            throw new RuntimeException("No tenés clases disponibles en este plan");
        }
        //Validar que no haya hecho una reserva en esa clase ya
        reservaRepository.reservaPorSocioyClaseId(socio.getId(), clase.getId())
                .ifPresent(r -> {
            throw new RuntimeException("Ya tenés una reserva para esta clase");
        });

        //Validar que no tenga reserva del mismo tipo de actividad, para el mismo dia
        List<Reserva> reservasMismaActividad = reservaRepository.reservasPorDiayTipoActividad(
                socio.getId(), clase.getDiaSemana(), clase.getTipoActividad().getId());

        if(!reservasMismaActividad.isEmpty()){
            throw new RuntimeException("Ya tenés una reserva de " + clase.getTipoActividad().getNombreTipoActividad() + " ese día");
        }
        //Validar que en el mismo horario no tenga otra reserva ese dia
        List<Reserva> reservasMismoHorario = reservaRepository.reservasMismoHorario(
                socio.getId(), clase.getDiaSemana(), clase.getHoraFin().toString(), clase.getHoraInicio().toString());

        if (!reservasMismoHorario.isEmpty()) {
            throw new RuntimeException("Ya tenés una reserva en ese horario");
        }
        //Validar que hayan cupos disponibles para la clase que quiere reservar
        int cuposDisponibles = claseRepository.cuposDisponibles(clase.getId());
        if(cuposDisponibles <= 0){
            throw new RuntimeException("No hay mas cupos disponibles para esta clase");
        }
        Reserva reserva = new Reserva();
        reserva.setClase(clase);
        reserva.setSocioPlan(socioPlan);
        reserva.setFechaHoraReserva(LocalDateTime.now());

        return reservaMapper.toResponse(reservaRepository.save(reserva));
    }
}
