package org.example.customspringaop.exceptions;

public class InvalidFoodException extends RuntimeException {
    public InvalidFoodException(String message) {
        super(message);
    }
}
