package com.damo.gestionDeStock.exception;

import lombok.Getter;

import java.util.List;

public class InvalideOperationException extends RuntimeException {
    @Getter
    private ErrorCodes errorCodes;

    public InvalideOperationException(String message, ErrorCodes articleNotFound, List<String> articleErrors) {
        super(message);
    }

    public InvalideOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalideOperationException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public InvalideOperationException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }
}
