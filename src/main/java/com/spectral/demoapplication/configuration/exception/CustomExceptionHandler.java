package com.spectral.demoapplication.configuration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Handler for exceptions that are explicitly thrown in the application.
 * */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> brokenConstraintExceptionHandler(HttpServletRequest request,
                                                                   EmptyResultDataAccessException e) {
        if (log.isDebugEnabled()) {
            log.debug("Form validation error occurred: URL = " + request.getRequestURL());
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
