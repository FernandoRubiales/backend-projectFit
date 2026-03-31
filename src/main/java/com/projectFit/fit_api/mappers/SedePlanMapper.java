package com.projectFit.fit_api.mappers;

import com.projectFit.fit_api.dto.SedePlanResponseDTO;
import com.projectFit.fit_api.entity.SedePlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SedePlanMapper {

    @Mapping(source = "sede.nombreSede", target = "nombreSede")
    @Mapping(source = "plan.nombrePlan", target = "nombrePlan")
    @Mapping(source = "plan.tipoActividad.nombreTipoActividad", target = "tipoActividad")
    SedePlanResponseDTO toResponse(SedePlan sedePlan);
}
