package br.com.example.springshop.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
    private String message;

    public ErrorDetails(LocalDateTime timestamp, Integer status, String error, String path, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
    }
}
