package com.projectFit.fit_api.mappers;

import com.projectFit.fit_api.dto.ClaseRequestDTO;
import com.projectFit.fit_api.dto.ClaseResponseDTO;
import com.projectFit.fit_api.entity.Clase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    //ClaseRequestDTO a Entidad Clase
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraBajaClase", ignore = true)
    @Mapping(target = "sede", ignore = true)
    @Mapping(target = "tipoActividad", ignore = true)
    Clase toEntity(ClaseRequestDTO claseRequestDTO);

    //Entidad Clase a ClaseResponseDTO
    @Mapping(source = "sede.nombreSede", target = "nombreSede")
    @Mapping(source = "tipoActividad.nombreTipoActividad", target = "nombreTipoActividad")
    @Mapping(source = "tipoActividad.requiereReserva", target = "requiereReserva")
    @Mapping(target = "cuposDisponibles", ignore = true)
    ClaseResponseDTO toResponse(Clase clase);
}
