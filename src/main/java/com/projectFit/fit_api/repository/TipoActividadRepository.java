package com.projectFit.fit_api.repository;


import com.projectFit.fit_api.entity.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad,Long> {

    Optional<TipoActividad> findByIdAndFechaHoraBajaTA(Long id);
}
