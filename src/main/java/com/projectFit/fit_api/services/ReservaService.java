package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.ReservaRequestDTO;
import com.projectFit.fit_api.dto.ReservaResponseDTO;
import com.projectFit.fit_api.entity.Clase;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.repository.ClaseRepository;
import com.projectFit.fit_api.repository.ReservaRepository;
import com.projectFit.fit_api.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final SocioRepository socioRepository;
    private final ClaseRepository claseRepository;

    //REALIZAR UNA RESERVA
    public ReservaResponseDTO realizarReserva(ReservaRequestDTO reservaRequestDTO, String auth0Id){
        Socio socio = socioRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        Clase clase = claseRepository.findById(reservaRequestDTO.getClaseId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        
    }
}
