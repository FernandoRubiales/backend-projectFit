package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.EscanerQrRequestDTO;
import com.projectFit.fit_api.dto.EscanerQrResponseDTO;
import com.projectFit.fit_api.entity.Reserva;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.entity.SocioPlan;
import com.projectFit.fit_api.repository.ReservaRepository;
import com.projectFit.fit_api.repository.SocioPlanRepository;
import com.projectFit.fit_api.repository.SocioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .orElseThrow(()-> new RuntimeException("Qr invalido, socio no encontrado"));

        //Buscar si tiene reserva para hoy en esa sede
        Reserva reserva = reservaRepository.reservaActivaPorSocioySede(socio.getId(), escanerQrRequestDTO.getSedeId()).orElse(null);

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

            //VALIDACIONES PARA CLASE SIN RESERVA

        }

    }
}
