package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.EstadoSocio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoSocioRepository extends JpaRepository<EstadoSocio, Long> {

    Optional<EstadoSocio> findByNombreEstadoSocio(String nombreEstadoSocio);
}
