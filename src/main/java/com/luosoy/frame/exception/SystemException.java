/**
 *
 */
package com.luosoy.frame.exception;

@SuppressWarnings("serial")
public class SystemException extends BaseException {

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
     * The Constant JMS_EXCEPTION.
     */
    public static final String JMS_EXCEPTION = "000104";

    /**
     * The Constant DATE_EXCEPTION.
     */
    public static final String DATE_EXCEPTION = "000105";

    /**
     * The Constant JAXB_EXCEPTION.
     */
    public static final String JAXB_EXCEPTION = "000106";

    /**
     * The Constant TYPE_MISMATCH.
     */
    public static final String TYPE_MISMATCH = "000107";

    /**
     * The Constant ENCRYPTION_ERROR.
     */
    public static final String ENCRYPTION_ERROR = "000108";

    /**
     * The Constant SERVICE_INVOCATION_ERROR.
     */
    public static final String SERVICE_INVOCATION_ERROR = "000109";

    /**
     * The Constant REFLECTION_EXCEPTION.
     */
    public static final String REFLECTION_EXCEPTION = "000110";

    /**
     * The Constant DEEPCLONE_EXCEPTION.
     */
    public static final String DEEPCLONE_EXCEPTION = "000111";

    /**
     * The Constant PROPERTIE_LOAD_EXCEPTION.
     */
    public static final String PROPERTIE_LOAD_EXCEPTION = "000112";

    /**
     * The Constant INCONSISTENCE_EXCEPTION.
     */
    public static final String INCONSISTENCE_EXCEPTION = "000113";

    /**
     * The Constant TEMPLATE_PARSE_EXCEPTION.
     */
    public static final String TEMPLATE_PARSE_EXCEPTION = "000114";

    /**
     * The Constant FILE_EXPORT_EXCEPTION.
     */
    public static final String FILE_EXPORT_EXCEPTION = "000115";

    /**
     * The Constant FUNCTION_NOT_SUPPORTED.
     */
    public static final String FUNCTION_NOT_SUPPORTED = "000116";

    /**
     * The Constant COMPRESS_EXCEPTION.
     */
    public static final String COMPRESS_EXCEPTION = "000117";

    /**
     * The Constant DATA_PARSE_EXCEPTION.
     */
    public static final String DATA_PARSE_EXCEPTION = "000118";

    /**
     * The Constant UNSUPPORTED_CHARSET_EXCEPTION.
     */
    public static final String UNSUPPORTED_CHARSET_EXCEPTION = "000119";

    /**
     * The Constant FILE_NOT_FOUND_EXCEPTION.
     */
    public static final String FILE_NOT_FOUND_EXCEPTION = "000120";

    /**
     * The Constant mu.
     */
//    private static final MessageUtils mu = SpringContextUtil.getBean("messageUtils", MessageUtils.class);
    /**
     * Instantiates a new system exception.
     *
     * @param message the message
     * @param cause the cause
     * @param errorCode the error code
     */
    public SystemException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    /**
     * Instantiates a new system exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public SystemException(String message, String errorCode) {
        super(message, new RuntimeException(message), errorCode);
    }

    /**
     * Instantiates a new system exception.
     *
     * @param cause the cause
     * @param errorCode the error code
     * @param args the args
     */
//    public SystemException(Throwable cause, String errorCode, Object... args) {
//        super(mu.getMessage(errorCode, args), cause, errorCode);
//    }
    /**
     * Instantiates a new system exception.
     *
     * @param errorCode the error code
     * @param args the args
     */
//    public SystemException(String errorCode, Object... args) {
//        super(mu.getMessage(errorCode, args), null, errorCode);
//    }
}
