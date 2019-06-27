package ru.com.melt.info.exception;

public class CantCompleteClientRequestException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public CantCompleteClientRequestException() {
    }

    public CantCompleteClientRequestException(String message) {
        super(message);
    }

    public CantCompleteClientRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
