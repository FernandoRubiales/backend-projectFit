package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SocioRepository extends JpaRepository<Socio,Long> {

    Optional<Socio> findByEmail(String email);
    Optional<Socio> findByDni(BigDecimal dni);
    Optional<Socio> findByqrCode(String qrCode);

    boolean existsByEmail(String email);
    boolean existsByDni(BigDecimal dni);
}
