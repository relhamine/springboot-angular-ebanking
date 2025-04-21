package ebanking.controller;

import ebanking.entities.ErrorDetails;
import ebanking.exceptions.BadRequestException;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BalanceNotSufficientException.class)
    public ResponseEntity<ErrorDetails> balanceNotSufficientHandler(BalanceNotSufficientException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorDetails);
    }

    @ExceptionHandler(BalanceExceedException.class)
    public ResponseEntity<ErrorDetails> balanceExceedHandler(BalanceExceedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> badRequestHandler(BadRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorDetails);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(errorDetails);
    }
}