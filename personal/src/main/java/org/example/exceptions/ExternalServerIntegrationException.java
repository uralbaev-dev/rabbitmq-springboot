package org.example.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ExternalServerIntegrationException extends RuntimeException {
    public ExternalServerIntegrationException(String message) {
        super(message);
    }
}
