package com.senlainc.warsaw.tyurin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseException> handleInternalServerException() {
        return new ResponseEntity<>(new ResponseException("Internal server exception", "500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ResponseException> handleMethodArgumentTypeMismatch() {
        return new ResponseEntity<>(new ResponseException("Invalid argument used in URL", "400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ExpectationFailedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    protected ResponseEntity<ResponseException> handleExpectationFailedException(ExpectationFailedException exception) {
        return new ResponseEntity<>(new ResponseException("Invalid search criteria " + exception.getParameter(), "417"), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseException> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new ResponseException("Invalid entity id = "
                + exception.getEntityId() + ". Entity not found", "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ResponseException> handleUserExistException(UserExistException exception) {
        return new ResponseEntity<>(new ResponseException("User with username "
                + exception.getUsername() + " already exist", "409"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<ResponseException> handleBadCredentialsException() {
        return new ResponseEntity<>(new ResponseException("Incorrect username or password", "401"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    protected ResponseEntity<ResponseException> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception) {
        return new ResponseEntity<>(new ResponseException(exception.getMessage(), "401"), HttpStatus.UNAUTHORIZED);
    }
}
