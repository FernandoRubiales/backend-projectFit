package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.EstadoSocioPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoSocioPlanRepository extends JpaRepository<EstadoSocioPlan, Long> {

    Optional<EstadoSocioPlan> findByNombreEstadoSocioPlan(String nombreEstadoSocioPlan);
}
