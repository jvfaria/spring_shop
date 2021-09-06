package br.com.example.springshop.exceptions;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(final String message) {
        super(message);
    }
}
