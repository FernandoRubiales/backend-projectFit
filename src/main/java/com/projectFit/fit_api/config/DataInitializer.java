package com.projectFit.fit_api.config;

import com.projectFit.fit_api.entity.EstadoSocioPlan;
import com.projectFit.fit_api.repository.EstadoSocioPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final EstadoSocioPlanRepository estadoSocioPlanRepository;

    @Override
    public void run(ApplicationArguments args) {
        inicializarEstadosSocioPlan();
    }

    private void inicializarEstadosSocioPlan() {
        if (estadoSocioPlanRepository.count() == 0) {
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null,"Pendiente"));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null,"Activo"));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null, "Vencido"));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null, "Suspendido"));
            System.out.println("Estados de SocioPlan inicializados");
        }
    }

}
