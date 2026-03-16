package com.projectFit.fit_api.mappers;

import com.projectFit.fit_api.dto.PlanRequestDTO;
import com.projectFit.fit_api.dto.PlanResponseDTO;
import com.projectFit.fit_api.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    //PlanRequestDTO a entidad Plan
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraBajaPlan", ignore = true)
    @Mapping(target = "tipoActividad", ignore = true)
    @Mapping(target = "clasesIncluidas", ignore = true)
    Plan toEntity(PlanRequestDTO planRequestDTO);

    //Entidad Plan a PlanResponseDTO
    @Mapping(source = "tipoActividad.nombreTipoActividad", target = "tipoActviidad")
    PlanResponseDTO toResponse(Plan plan);

}
