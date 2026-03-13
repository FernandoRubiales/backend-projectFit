package com.projectFit.fit_api.services;

import com.projectFit.fit_api.dto.SocioRequestDTO;
import com.projectFit.fit_api.dto.SocioResponseDTO;
import com.projectFit.fit_api.entity.Socio;
import com.projectFit.fit_api.mappers.SocioMapper;
import com.projectFit.fit_api.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;

    //CREATE SOCIO
    public SocioResponseDTO crearSocio(SocioRequestDTO socioRequestDTO){
        if (socioRepository.existsByDni(socioRequestDTO.getDni())) {
            throw new RuntimeException("Ya existe un socio con ese DNI");
        }
        if (socioRepository.existsByEmail(socioRequestDTO.getEmail())) {
            throw new RuntimeException("Ya existe un socio con ese email");
        }

        Socio socio = socioMapper.toEntity(socioRequestDTO);
        socio.setQrCode(UUID.randomUUID().toString());
        Socio socioGuardado = socioRepository.save(socio);
        return socioMapper.toResponse(socioGuardado);
    }

    //UPDATE SOCIO
    public SocioResponseDTO actualizarSocio(Long id, SocioRequestDTO socioRequestDTO){
        Socio socioExistente = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + id));
        socioExistente.setNombre(socioRequestDTO.getNombre());
        socioExistente.setApellido(socioRequestDTO.getApellido());
        socioExistente.setTelefono(socioRequestDTO.getTelefono());
        socioExistente.setFechaNacimiento(socioRequestDTO.getFechaNacimiento());
        Socio socioGuardado = socioRepository.save(socioExistente);
        return socioMapper.toResponse(socioGuardado);
    }

    //DELETE SOCIO
    public void eliminarSocio(Long id) {
        Socio socioExistente = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + id));
        socioRepository.delete(socioExistente);
    }

    //GET ID SOCIO
    public SocioResponseDTO obtenerPorId(Long id) {
        Socio socioExistente = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + id));
        return socioMapper.toResponse(socioExistente);
    }

    //GET ALL SOCIOS
    public List<SocioResponseDTO> obtenerTodos() {
        return socioRepository.findAll()
                .stream()
                .map(socioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
