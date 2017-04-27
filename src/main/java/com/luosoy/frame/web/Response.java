package com.luosoy.frame.web;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Response<T extends Object> implements Serializable {

    private static final long serialVersionUID = 1134838166895981951L;

    /**
     * Message Type.
     */
    public enum Type {
        /**
         * success.
         */
        SUCCESS,
        /**
         * warn.
         */
        WARN,
        /**
         * error.
         */
        ERROR
    }

    private Type type;

    private String content;

    private T data;

    /**
     * Success response with data
     *
     * @param data
     */
    public Response(T data) {
        this(data, Type.SUCCESS);
    }

    /**
     *
     * @param data
     * @param type
     */
    public Response(T data, Type type) {
        this.data = data;
        this.type = type;
    }

    /**
     * @param type
     * @param content
     */
    public Response(Type type, String content) {
        this(null, type, content);
    }

    /**
     * @param data
     * @param type
     * @param content
     */
    public Response(T data, Type type, String content) {
        this.data = data;
        this.type = type;
        this.content = content;
    }

    /**
     * @return
     */
    public static Response<String> success() {
        return new Response<String>("", Type.SUCCESS);
    }

    /**
     * @param <T>
     * @param data
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>(data, Type.SUCCESS);
    }

    /**
     * @param <T>
     * @param data
     * @return
     */
    public static <T> Response<T> warn(T data) {
        return new Response<T>(data, Type.WARN);
    }

    /**
     *
     * @param <T>
     * @param content
     * @return
     */
    public static <T> Response<T> warn(String content) {
        return new Response<T>(Type.WARN, content);
    }

    /**
     *
     * @param content
     * @return
     */
    public static <T> Response<T> error(String content) {
        return new Response<T>(Type.ERROR, content);
    }

    /**
     *
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * (non-Javadoc).
     *
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
