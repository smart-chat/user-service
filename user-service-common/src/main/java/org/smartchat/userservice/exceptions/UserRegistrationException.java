package org.smartchat.userservice.exceptions;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
    }

    public UserRegistrationException(String message) {
        super(message);
    }
}
