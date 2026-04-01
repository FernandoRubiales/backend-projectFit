package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.TipoActividadRequestDTO;
import com.projectFit.fit_api.dto.TipoActividadResponseDTO;
import com.projectFit.fit_api.entity.TipoActividad;
import com.projectFit.fit_api.mappers.TipoActividadMapper;
import com.projectFit.fit_api.repository.TipoActividadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoActividadService {

    private final TipoActividadRepository tipoActividadRepository;
    private final TipoActividadMapper tipoActividadMapper;

    //CREATE TipoActividad
    public TipoActividadResponseDTO crearActividad(TipoActividadRequestDTO tipoActividadRequestDTO){
        if(tipoActividadRepository.existsByNombreTipoActividad(tipoActividadRequestDTO.getNombreTipoActividad())){
            throw new RuntimeException("Ya existe una actividad con ese nombre");
        }
        TipoActividad tipoActividad = tipoActividadMapper.toEntity(tipoActividadRequestDTO);
        TipoActividad tipoActividadGuardado = tipoActividadRepository.save(tipoActividad);

        return tipoActividadMapper.toResponse(tipoActividadGuardado);
    }

    //UPDATE TipoActividad
    public TipoActividadResponseDTO actualizarActividad(Long id, TipoActividadRequestDTO tipoActividadRequestDTO){
        TipoActividad tipoActividadExistente = tipoActividadRepository.findByIdAndFechaHoraBajaActividadIsNull(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        tipoActividadExistente.setNombreTipoActividad(tipoActividadRequestDTO.getNombreTipoActividad());
        tipoActividadExistente.setDescripcion(tipoActividadRequestDTO.getDescripcion());
        tipoActividadExistente.setRequiereReserva(tipoActividadRequestDTO.getRequiereReserva());

        TipoActividad tipoActividadGuardado = tipoActividadRepository.save(tipoActividadExistente);
        return tipoActividadMapper.toResponse(tipoActividadGuardado);
    }

    //DELETE TipoActividad
    public void darDeBajaActividad(Long id){
        TipoActividad tipoActividadExistente = tipoActividadRepository.findByIdAndFechaHoraBajaActividadIsNull(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        tipoActividadExistente.setFechaHoraBajaActivdad(LocalDateTime.now());
        tipoActividadRepository.save(tipoActividadExistente);
    }

    //GET TipoActividad por ID
    public TipoActividadResponseDTO obtenerPorId(Long id){
        TipoActividad tipoActividad = tipoActividadRepository.findByIdAndFechaHoraBajaActividadIsNull(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        return tipoActividadMapper.toResponse(tipoActividad);
    }

    //GET ALL TipoActividad
    public List<TipoActividadResponseDTO> obtenerTodas(){
        return tipoActividadRepository.findByFechaHoraBajaActividadIsNull()
                .stream()
                .map(tipoActividadMapper::toResponse)
                .toList();
    }
}
