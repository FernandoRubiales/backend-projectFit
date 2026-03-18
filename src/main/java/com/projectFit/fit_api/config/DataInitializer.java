package com.projectFit.fit_api.config;

import com.projectFit.fit_api.entity.EstadoSocio;
import com.projectFit.fit_api.entity.EstadoSocioPlan;
import com.projectFit.fit_api.repository.EstadoSocioPlanRepository;
import com.projectFit.fit_api.repository.EstadoSocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final EstadoSocioPlanRepository estadoSocioPlanRepository;
    private final EstadoSocioRepository estadoSocioRepository;

    @Override
    public void run(ApplicationArguments args) {
        inicializarEstadosSocioPlan();
        inicializarEstadosSocio();
    }

    private void inicializarEstadosSocioPlan() {
        if (estadoSocioPlanRepository.count() == 0) {
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null,"Pendiente", null));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null,"Activo", null));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null, "Vencido", null));
            estadoSocioPlanRepository.save(new EstadoSocioPlan(null, "Suspendido", null));
            System.out.println("Estados de SocioPlan inicializados");
        }
    }

    private void inicializarEstadosSocio() {
        if (estadoSocioRepository.count() == 0) {
            estadoSocioRepository.save(new EstadoSocio(null, "Activo", null));
            estadoSocioRepository.save(new EstadoSocio(null, "Inactivo", null));
            System.out.println("Estados de Socio inicializados");
        }
    }
}
