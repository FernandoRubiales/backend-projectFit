package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.SedeRequestDTO;
import com.projectFit.fit_api.dto.SedeResponseDTO;
import com.projectFit.fit_api.entity.Sede;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.mappers.SedeMapper;
import com.projectFit.fit_api.repository.SedeRepository;
import com.projectFit.fit_api.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;
    private final SocioRepository socioRepository;

    //CREATE SEDE
    public SedeResponseDTO crearSede(SedeRequestDTO sedeRequestDTO){
        if(sedeRepository.existsByDireccion(sedeRequestDTO.getDireccion())){
            throw new RuntimeException("Ya existe una sede con ese nombre");
        }
        Sede sede = sedeMapper.toEntity(sedeRequestDTO);
        Sede sedeGuardada = sedeRepository.save(sede);
        return sedeMapper.toResponse(sedeGuardada);
    }

    //UPDATE SEDE
    public SedeResponseDTO actualizarSede(Long id, SedeRequestDTO sedeRequestDTO){
        Sede sedeExistente = sedeRepository.findByIdAndFechaHoraBajaIsNull(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        sedeExistente.setNombreSede(sedeRequestDTO.getNombreSede());
        sedeExistente.setTelefono(sedeRequestDTO.getTelefono());
        sedeExistente.setDireccion(sedeRequestDTO.getDireccion());
        Sede sedeGuardada = sedeRepository.save(sedeExistente);
        return sedeMapper.toResponse(sedeGuardada);
    }

    //DELETE SEDE
    public void darDeBajaSede(Long id){
        Sede sedeExistente = sedeRepository.findByIdAndFechaHoraBajaIsNull(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        sedeExistente.setFechaHoraBajaSede(LocalDateTime.now());
        sedeRepository.save(sedeExistente);
    }

    //GET SEDE por ID
    public SedeResponseDTO obtenerPorId(Long id) {
        Sede sedeExistente = sedeRepository.findByIdAndFechaHoraBajaIsNull(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        return sedeMapper.toResponse(sedeExistente);
    }

    //GET ALL SEDE
    public List<SedeResponseDTO> obtenerTodas() {
        return sedeRepository.findByFechaHoraBajaSedeIsNull()
                .stream()
                .map(sedeMapper::toResponse)
                .toList();
    }

    //GET DE LAS SEDES QUE PUEDE IR EL SOCIO DEPENDIENDO LOS PLANES ACTIVOS QUE TENGA
    public List<SedeResponseDTO> obtenerSedesPlanesActivos(String auth0Id) {
        Socio socio = socioRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
        return sedeRepository.findSedesParaPlanesActivos(socio.getId())
                .stream()
                .map(sedeMapper::toResponse)
                .toList();
    }

}
