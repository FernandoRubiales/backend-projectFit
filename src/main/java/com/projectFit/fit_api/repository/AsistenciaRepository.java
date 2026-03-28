package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {


}
