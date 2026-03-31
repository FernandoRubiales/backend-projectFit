package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.SedePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedePlanRepository extends JpaRepository <SedePlan,Long> {

    //Query para ver si ya existe ese plan en esa sede
    @Query(value = "SELECT COUNT(*) > 0 FROM sede_plan " +
            "WHERE sede_id = :sedeId " +
            "AND plan_id = :planId " +
            "AND fecha_hora_baja_sede_plan IS NULL",
            nativeQuery = true)
    boolean existePlanEnSede(@Param("sedeId") Long sedeId, @Param("planId") Long planId);


    //Query para ver que planes disponibles tiene una sede
    @Query(value = "SELECT * FROM sede_plan"+
            "WHERE sede_id = :sedeId"+
            "AND fecha_hora_baja_sede_plan IS NULL", nativeQuery = true)
    List<SedePlan> planesDisponiblesEnSede(@Param("sedeId") Long sedeId);


}
