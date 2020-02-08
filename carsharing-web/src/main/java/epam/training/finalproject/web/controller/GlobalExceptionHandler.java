package epam.training.finalproject.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadCredentialsException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This request has a bad credentials");
    }
}
