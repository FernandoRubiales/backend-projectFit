package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.ClaseRequestDTO;
import com.projectFit.fit_api.dto.ClaseResponseDTO;
import com.projectFit.fit_api.entity.Clase;
import com.projectFit.fit_api.entity.Sede;
import com.projectFit.fit_api.entity.TipoActividad;
import com.projectFit.fit_api.mappers.ClaseMapper;
import com.projectFit.fit_api.repository.ClaseRepository;
import com.projectFit.fit_api.repository.SedeRepository;
import com.projectFit.fit_api.repository.TipoActividadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final SedeRepository sedeRepository;
    private final TipoActividadRepository tipoActividadRepository;
    private final ClaseMapper claseMapper;

    //CREATE CLASE
    public ClaseResponseDTO crearClase(ClaseRequestDTO claseRequestDTO){
        Sede sede = sedeRepository.findByIdAndFechaHoraBajaIsNull(claseRequestDTO.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        TipoActividad tipoActividad = tipoActividadRepository.findByIdAndFechaHoraBajaTAIsNull(claseRequestDTO.getTipoActividadId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        Clase clase = claseMapper.toEntity(claseRequestDTO);
        clase.setSede(sede);
        clase.setTipoActividad(tipoActividad);
        Clase claseGuardada = claseRepository.save(clase);
        return claseMapper.toResponse(claseGuardada);
    }
}
