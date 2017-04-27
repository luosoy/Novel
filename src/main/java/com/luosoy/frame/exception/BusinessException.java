/**
 *
 */
package com.luosoy.frame.exception;

@SuppressWarnings("serial")
public class BusinessException extends BaseException {

    /**
     * The Constant REQUEST_EXCEPTION.
     */
    // 00 means System Exception, 01 means web, 01 means request error
    public static final String REQUEST_EXCEPTION = "000101";

    /**
     * The Constant RESPONSE_EXCEPTION.
     */
    public static final String RESPONSE_EXCEPTION = "000102";

    /**
     * The Constant PERSIST_EXCEPTION.
     */
    public static final String PERSIST_EXCEPTION = "000103";

    /**
     * The Constant BASECODE_EXCEPTION.
     */
    public static final String BASECODE_EXCEPTION = "000202";

    /**
     * The Constant JSON_EXCEPTION.
     */
    public static final String JSON_EXCEPTION = "000203";

    /**
     * The Constant REQUEST_PARAM_EXCEPTION.
     */
    public static final String REQUEST_PARAM_EXCEPTION = "000204";

    /**
     * Instantiates a new business exception.
     *
     * @param message the message
     * @param cause the cause
     * @param errorCode the error code
     */
    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    /**
     * Instantiates a new business exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public BusinessException(String message, String errorCode) {
        super(message, null, errorCode);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause, REQUEST_EXCEPTION);
    }

    public BusinessException(String message) {
        super(message, null, REQUEST_EXCEPTION);
    }
}
