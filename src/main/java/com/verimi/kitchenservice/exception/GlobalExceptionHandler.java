package com.verimi.kitchenservice.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
        Exception ex, WebRequest request) {
        return new ResponseEntity<>(
            ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN );
    }

    @ExceptionHandler({ StaffMemberNotFoundException.class })
    public ResponseEntity<Object> handleUnknownStaffMemberException(
        Exception ex, WebRequest request) {
        return new ResponseEntity<>(
            ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST );
    }
}
