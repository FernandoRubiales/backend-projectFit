package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.EscanerQrRequestDTO;
import com.projectFit.fit_api.dto.EscanerQrResponseDTO;
import com.projectFit.fit_api.entity.Reserva;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.entity.SocioPlan;
import com.projectFit.fit_api.exception.ResourceNotFoundException;
import com.projectFit.fit_api.repository.ReservaRepository;
import com.projectFit.fit_api.repository.SocioPlanRepository;
import com.projectFit.fit_api.repository.SocioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QrService {

    private final SocioRepository socioRepository;
    private final ReservaRepository reservaRepository;
    private final SocioPlanRepository socioPlanRepository;

    //Procesar el escaneo del Qr una vez que ingresa al predio
    @Transactional
    public EscanerQrResponseDTO escanearQr(EscanerQrRequestDTO escanerQrRequestDTO){

        //Se busca al socio por su propio QR
        Socio socio = socioRepository.findByqrCode(escanerQrRequestDTO.getQrCode())
                .orElseThrow(()-> new ResourceNotFoundException("Qr invalido, socio no encontrado"));

        //Buscar si tiene reserva para hoy en esa sede
        Reserva reserva = reservaRepository.reservaActivaPorSocio(socio.getId(), LocalTime.now().toString()).orElse(null);

        if(reserva != null){
            //VALIDACIONES PARA CLASE CON RESERVA
            SocioPlan socioPlan = reserva.getSocioPlan();
            //Descontamos la cantidad de clases disponibles que le quedan
            socioPlan.setClasesDisponibles(socioPlan.getClasesDisponibles() - 1);
            socioPlanRepository.save(socioPlan);

            EscanerQrResponseDTO escanerQrResponseDTO = new EscanerQrResponseDTO();
            escanerQrResponseDTO.setNombreSocio(socio.getNombre());
            escanerQrResponseDTO.setApellidoSocio(socio.getApellido());
            escanerQrResponseDTO.setClasesRestante(socioPlan.getClasesDisponibles());
            escanerQrResponseDTO.setNombreTipoActividad(reserva.getClase().getTipoActividad().getNombreTipoActividad());
            escanerQrResponseDTO.setMensaje("¡Bienvenido " + socio.getNombre());

            return escanerQrResponseDTO;
        }
        //VALIDACIONES PARA CLASES SIN RESERVA
        SocioPlan socioPlanSinReserva = socioPlanRepository.planActivoporSocio(socio.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No tenes plan activo"));

        //Descontamos la cantidad de clases disponibles que le quedan
        socioPlanSinReserva.setClasesDisponibles(socioPlanSinReserva.getClasesDisponibles() - 1);
        socioPlanRepository.save(socioPlanSinReserva);

        EscanerQrResponseDTO escanerQrResponseDTO = new EscanerQrResponseDTO();
        escanerQrResponseDTO.setNombreSocio(socio.getNombre());
        escanerQrResponseDTO.setApellidoSocio(socio.getApellido());
        escanerQrResponseDTO.setNombreTipoActividad(socioPlanSinReserva.getPlan().getTipoActividad().getNombreTipoActividad());
        escanerQrResponseDTO.setClasesRestante(socioPlanSinReserva.getClasesDisponibles());
        escanerQrResponseDTO.setMensaje("¡Bienvenido " + socio.getNombre());

        return escanerQrResponseDTO;
    }
}
