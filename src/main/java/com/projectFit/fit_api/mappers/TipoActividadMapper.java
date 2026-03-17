package com.projectFit.fit_api.mappers;

import com.projectFit.fit_api.dto.TipoActividadRequestDTO;
import com.projectFit.fit_api.dto.TipoActividadResponseDTO;
import com.projectFit.fit_api.entity.TipoActividad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoActividadMapper {

    //TipoActividadRequestDTO a entidad TipoActividad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraBajaTA", ignore = true)
    TipoActividad toEntity(TipoActividadRequestDTO tipoActividadRequestDTO);

    //Entidad TipoActividad a TipoActividadResponseDTO
    TipoActividadResponseDTO toResponse(TipoActividad tipoActividad);


}
