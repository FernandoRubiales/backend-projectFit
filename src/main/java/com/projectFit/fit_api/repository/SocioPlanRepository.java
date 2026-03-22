package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.SocioPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocioPlanRepository extends JpaRepository<SocioPlan, Long> {


    //Query para verificar si el socio ya tiene ese plan activo
    @Query(value = "SELECT sp.* FROM socio_plan sp" +
            "JOIN estado_socio_plan.esp ON sp.estado_id = esp.id"+
            "WHERE sp.socio_id = :socioId"+
            "AND sp.plan_id = :planId"+
            "AND esp.nombre_estado_socio_plan = 'Activo'"
            , nativeQuery = true)
    Optional<SocioPlan> planActivoporSocioyPlanId(
            @Param("socioId") Long socioId,
            @Param("planId") Long planId);
}
