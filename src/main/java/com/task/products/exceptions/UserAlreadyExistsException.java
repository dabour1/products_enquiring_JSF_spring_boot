package com.task.products.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User with the given username or email already exists");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
