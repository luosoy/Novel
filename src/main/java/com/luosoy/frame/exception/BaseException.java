package com.luosoy.frame.exception;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

    /**
     * The error code.
     */
    private final String errorCode;

    /**
     * Instantiates a new base exception.
     *
     * @param message the message
     * @param cause the cause
     * @param errorCode the error code
     */
    public BaseException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}
