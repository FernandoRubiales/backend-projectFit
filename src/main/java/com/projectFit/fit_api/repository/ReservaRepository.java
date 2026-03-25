package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva, Long> {

    //Query para verificar si el socio ya reservo esa clase antes
    Optional<Reserva> reservaPorSocioyClaseId(@Param("socioId") Long socioId,
                                              @Param("claseId") Long claseId);

    //Query para verificar si el socio tiene reservas del mismo tipo de actividad para el mismo dia
    List<Reserva> reservasPorDiayTipoActividad(@Param("socioId") Long socioId,
                                               @Param("diaSemana") String diaSemana,
                                               @Param("tipoActividadId") Long tipoActividadId);

    //Query para verificar si el socio tiene otra reserva en el mismo horario para el mismo dia
    List<Reserva> reservasMismoHorario(@Param("socioId") Long socioId,
                                       @Param("diaSemana") String diaSemana,
                                       @Param("horaInicio") String horaInicio,
                                       @Param("horaFin") String horaFin);


}
