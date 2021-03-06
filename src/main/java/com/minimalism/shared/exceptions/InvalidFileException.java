package com.minimalism.shared.exceptions;

public class InvalidFileException extends Exception {
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException(String message, Throwable exception) {
        super(message, exception);
    }
}
