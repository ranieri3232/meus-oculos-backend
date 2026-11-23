package dev.rlucassmd.meusOculosApi.controllers.exceptions;

import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(ResourceNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;

        error.setTimestamp(Instant.now());
        error.setError("Resource not found!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());

        return ResponseEntity.status(status).body(error);
    }
}
