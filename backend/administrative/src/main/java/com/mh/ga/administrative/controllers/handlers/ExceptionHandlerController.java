package com.mh.ga.administrative.controllers.handlers;

import com.mh.ga.administrative.models.responses.StandardErrorResponse;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardErrorResponse> illegalArgument(
            IllegalArgumentException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(Clock.systemUTC()),
                status.value(),
                status.name(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> resourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(Clock.systemUTC()),
                status.value(),
                status.name(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardErrorResponse> dataIntegrity(
            DataIntegrityException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardErrorResponse response = new StandardErrorResponse(
                Instant.now(Clock.systemUTC()),
                status.value(),
                status.name(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(response);
    }

}
