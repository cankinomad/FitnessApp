package org.berka.exception;

import lombok.Getter;

@Getter
public class CardManagerException extends RuntimeException{

    private final ErrorType errorType;

    public CardManagerException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }


    public CardManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
