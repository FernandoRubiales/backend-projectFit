package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Clase;
import com.projectFit.fit_api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepository extends JpaRepository <Clase,Long> {

    //Cupos disponibles para la clase (cupo maximo - cantidad de reservas realizadas)
    @Query(value = "SELECT c.cupo_maximo - COUNT(r.id) FROM clase c " +
            "LEFT JOIN reserva r ON r.clase_id = c.id " +
            "WHERE c.id = :claseId", nativeQuery = true)
    int cuposDisponibles(@Param("claseId") Long claseId);

    //CLASES DISPONIBLES PARA UNA SEDE
    @Query(value = "SELECT * FROM clase WHERE sede_id = :sedeId " +
            "AND fecha_hora_baja_clase IS NULL",
            nativeQuery = true)
    List<Clase> clasesDisponiblesPorSede(@Param("sedeId") Long sedeId);

    //CLASES DISPONIBLES PARA UN TIPO DE ACTIVIDAD
    @Query(value = "SELECT * FROM clase WHERE tipo_actividad_id = :tipoActividadId"+
    "AND fecha_hora_baja_clase IS NULL", nativeQuery = true)
    List<Clase> clasesDisponiblesPorTipoActividad(@Param("tipoActividadId") Long tipoActividadId);

    //CLASE DISPONIBLES POR SEDE Y DIA DE SEMANA
    @Query(value = "SELECT * FROM clase WHERE sede_id = :sedeId " +
            "AND dia_semana = :diaSemana " +
            "AND fecha_hora_baja_clase IS NULL",
            nativeQuery = true)
    List<Clase> clasesDisponiblesPorSedeYdia(@Param("sedeId") Long sedeId, @Param("diaSemana") String diaSemana);

    //CLASE ACTIVA POR SEDE Y RANGO HORARIO ACTUAL USADA PARA EL ESCANER Y VER QUE CLASE ESTA EN CURSO
    @Query(value = "SELECT * FROM clase WHERE sede_id = :sedeId " +
            "AND dia_semana = :diaSemana " +
            "AND hora_inicio <= :horaActual " +
            "AND hora_fin >= :horaActual " +
            "AND fecha_hora_baja_clase IS NULL " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Clase> claseEnCurso(@Param("sedeId") Long sedeId,
                                 @Param("diaSemana") String diaSemana,
                                 @Param("horaActual")LocalTime horaActual);

    //CLASES DONDE EL SOCIO PUEDE RESERVAR SEGUN SU PLAN ACTIVO
    @Query( value = "SELECT DISTINCT c.* FROM clase c " +
            "JOIN tipo_actividad ta ON c.tipo_actividad_id = ta.id " +
            "JOIN plan p ON p.tipo_actividad_id = ta.id " +
            "JOIN socio_plan sp ON sp.plan_id = p.id " +
            "JOIN estado_socio_plan esp ON sp.estado_id = esp.id " +
            "WHERE c.sede_id = :sedeId " +
            "AND  c.dia_semana = :diaActual" +
            "AND c.fecha_hora_baja_clase IS NULL " +
            "AND ta.requiere_reserva = true"+
            "AND esp.nombre_estado_socio_plan = 'Activo' " +
            "AND sp.socio_id = :socioId " +
            "AND (SELECT c.cupo_maximo - COUNT(r.id) FROM reserva r " +
            "     WHERE r.clase_id = c.id) > 0"+
            "     AND r.fecha_clase_reservada = CURRENT_DATE) > 0", nativeQuery = true)
    List<Clase> clasesDisponiblesParaSocio(@Param("sedeId") Long sedeId,
                                           @Param("socioId") Long socioId,
                                           @Param("diaActual") String diaActual);
}
