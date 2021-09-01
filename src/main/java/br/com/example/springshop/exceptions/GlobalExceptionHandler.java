package br.com.example.springshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<?> handleException(EmailExistsException exception, HttpServletRequest req) {
        LocalDateTime now = LocalDateTime.now();
        ErrorDetails errorDetails = new ErrorDetails(
                now,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                req.getRequestURL().toString(),
                exception.getMessage()
        );
        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException exception, HttpServletRequest req) {
        LocalDateTime now = LocalDateTime.now();
        ErrorDetails errorDetails = new ErrorDetails(
                now,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                req.getRequestURL().toString(),
                exception.getMessage()
        );
        return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
    }

}
