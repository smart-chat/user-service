package org.smartchat.userservice.exceptions;

public class LoginException extends RuntimeException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
