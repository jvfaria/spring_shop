package br.com.example.springshop.exceptions;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
