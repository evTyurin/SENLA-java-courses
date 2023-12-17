package com.senlainc.warsaw.tyurin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<ResponseException> handleEntityNotFound() {
        return new ResponseEntity<>(new ResponseException("entity not found", "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseException> handleInternalServerException() {
        return new ResponseEntity<>(new ResponseException("internal server exception", "500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ResponseException> handleMethodArgumentTypeMismatch() {
        return new ResponseEntity<>(new ResponseException("invalid argument used for search", "400"), HttpStatus.BAD_REQUEST);
    }
}
