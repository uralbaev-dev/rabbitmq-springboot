package org.example.handler;


import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.log4j.Log4j2;
import org.example.exceptions.ExternalServerIntegrationException;
import org.example.exceptions.ForbiddenException;
import org.example.exceptions.PersonalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ExceptionResponse> handleRateLimitException(RequestNotPermitted ex) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(400)
                                .message("Too many requests - please try again later")
                                .code(BAD_REQUEST.getReasonPhrase())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    @ExceptionHandler(PersonalNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePersonalNotFoundException(PersonalNotFoundException exp) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(NOT_FOUND.value())
                                .message(exp.getMessage())
                                .code(NOT_FOUND.getReasonPhrase())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        logExceptionDetails(request, ex);
        return ResponseEntity
                .status(FORBIDDEN)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(FORBIDDEN.value())
                                .message(ex.getMessage())
                                .code(FORBIDDEN.getReasonPhrase())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    @ExceptionHandler(ExternalServerIntegrationException.class)
    public ResponseEntity<ExceptionResponse> handleExternalIntegrationException(ExternalServerIntegrationException ex, WebRequest request) {
        logExceptionDetails(request, ex);
        return ResponseEntity
                .status(SERVICE_UNAVAILABLE)
                .body(ExceptionResponse.builder()
                        .errorCode(SERVICE_UNAVAILABLE.value())
                        .message(ex.getMessage())
                        .code(SERVICE_UNAVAILABLE.getReasonPhrase())
                        .timestamp(System.currentTimeMillis())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logExceptionDetails(request, ex);
        return ResponseEntity
                .status(UNPROCESSABLE_ENTITY)
                .body(ExceptionResponse.builder()
                        .errorCode(UNPROCESSABLE_ENTITY.value())
                        .message(ex.getMessage())
                        .code(UNPROCESSABLE_ENTITY.getReasonPhrase())
                        .timestamp(System.currentTimeMillis())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = exp.getBindingResult().getFieldErrors();
        for(FieldError fieldError: fieldErrors){
            sb.append(fieldError.getDefaultMessage()).append(", ");
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(BAD_REQUEST.value())
                                .message(sb.toString().trim())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    private void logExceptionDetails(WebRequest request, Exception ex) {
        log.error("Transaction ID: {}", request.getAttribute("transactionId", WebRequest.SCOPE_REQUEST));
        log.error("Exception occurred for URL: {}", request.getDescription(true));
        log.error("Exception class: {}", ex.getClass().getName());
        log.error("Exception message: {}", ex.getMessage());
    }
}
