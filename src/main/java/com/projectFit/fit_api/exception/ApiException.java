package com.projectFit.fit_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {

    private int status;
    private String error;
    private String mensaje;
    private LocalDateTime timestamp;
}
