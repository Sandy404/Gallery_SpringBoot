package com.example.gallery.advice;


import com.example.gallery.advice.exception.IdNotFoundException;
import com.example.gallery.advice.exception.UserNotFoundException;
import com.example.gallery.model.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        com.example.gallery.model.ErrorStructure error = new ErrorStructure(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IdNotFoundException.class, UserNotFoundException.class})
    public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorStructure error = new ErrorStructure(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentsException(IllegalArgumentException ex, WebRequest request) throws Exception {
        ErrorStructure error = new ErrorStructure(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
