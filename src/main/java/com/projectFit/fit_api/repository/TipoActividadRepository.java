package com.projectFit.fit_api.repository;


import com.projectFit.fit_api.entity.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad,Long> {

    List<TipoActividad> findByFechaHoraBajaTAIsNull();
    Optional<TipoActividad> findByIdAndFechaHoraBajaTAIsNull(Long id);
    boolean existsByNombreTipoActividad(String nombreTipoActividad);


}
