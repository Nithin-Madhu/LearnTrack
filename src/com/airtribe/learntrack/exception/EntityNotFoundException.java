package com.airtribe.learntrack.exception;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException() {
        super("Entity Not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
