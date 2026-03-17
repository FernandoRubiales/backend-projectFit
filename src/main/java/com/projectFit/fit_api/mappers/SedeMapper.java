package com.projectFit.fit_api.mappers;

import com.projectFit.fit_api.dto.SedeRequestDTO;
import com.projectFit.fit_api.dto.SedeResponseDTO;
import com.projectFit.fit_api.entity.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SedeMapper {

    //SedeRequestDTO a entidad Sede
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraBajaSede", ignore = true)
    Sede toEntity(SedeRequestDTO sedeRequestDTO);

    //Entidad Sede a SedeResponseDTO
    SedeResponseDTO toResponse(Sede sede);
}
