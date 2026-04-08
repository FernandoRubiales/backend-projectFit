package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {

    List<Sede> findByFechaHoraBajaSedeIsNull();

    Optional<Sede> findByIdAndFechaHoraBajaSedeIsNull(Long id);

    boolean existsByDireccion(String direccion);

    //Obtener las sedes del socioplan activo que tenga el socio
    @Query(value = "SELECT DISTINCT s.* FROM sede s" +
    "JOIN sede_plan sp ON s.id = sp.sede_id "+
    "JOIN plan p ON sp.plan_id = p.id"+
    "JOIN socio_plan sociop ON p.id = sociop.plan_id"+
    "JOIN estado_socio_plan esp ON sociop.estado_id = esp.id"+
    "WHERE sociop.socio_id =: socioId"+
    "AND esp.nombre_estado_socio_plan = 'Activo'"+
    "AND sp.fecha_baja_sede_plan IS NULL"+
    "AND s.fecha_hora_baja_sede IS NULL", nativeQuery = true)
    List<Sede> findSedesParaPlanesActivos(@Param("socioId") Long socioId);

}
