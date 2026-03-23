package com.projectFit.fit_api.repository;

import com.projectFit.fit_api.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    //Query para buscar pago por el ID de mercado pago
    @Query(value = "SELECT * FROM pago WHERE mp_payment_id = :mpPaymentId", nativeQuery = true)
    Optional<Pago> findByMpPaymentId(@Param("mpPaymentId") String mpPaymentId);
}
