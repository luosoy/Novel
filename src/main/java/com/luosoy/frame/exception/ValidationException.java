package com.luosoy.frame.exception;

@SuppressWarnings("serial")
public class ValidationException extends BusinessException {

    /**
     * The Constant VALIDATION_FAILED.
     */
    // 01 means Business Exception, 01 means web, 01 means validation error
    public static final String VALIDATION_FAILED = "010101";

    /**
     * The validation result.
     */
    private ValidationResult validationResult;// NOSONAR

    /**
     * Instantiates a new validation exception.
     *
     * @param validationResult the validation result
     */
    public ValidationException(ValidationResult validationResult) {
        super("验证错误", null, VALIDATION_FAILED);
        this.validationResult = validationResult;
    }

    /**
     * Instantiates a new validation exception.
     *
     * @param message the message
     * @param cause the cause
     * @param errorCode the error code
     */
    public ValidationException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    /**
     * Gets the validation result.
     *
     * @return the validation result
     */
    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
