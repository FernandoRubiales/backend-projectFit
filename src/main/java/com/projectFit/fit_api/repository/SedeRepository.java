package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {

    List<Sede> findByFechaHoraBajaSedeIsNull();

    Optional<Sede> findByIdAndFechaHoraBajaIsNull(Long id);

    boolean existsByDireccion(String direccion);


}
