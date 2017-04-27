package com.luosoy.frame.jpa.identity;

/**
 * Holds all context information needed for building an id.
 *
 * @author luozp
 *
 */
public class Context {

    /**
     * Target entity.
     */
    private Object entity;

    /**
     * Additional parameter.
     */
    private Object param;

    /**
     * Instantiates a new context.
     */
    Context() {

    }

    /**
     * Instantiates a new context.
     *
     * @param entity the entity
     * @param param the param
     */
    Context(Object entity, String param) {
        this.entity = entity;
        this.param = param;
    }

    /**
     * Gets the target entity.
     *
     * @return the target entity
     */
    public Object getEntity() {
        return entity;
    }

    /**
     * Sets the target entity.
     *
     * @param entity the new target entity
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * Gets the additional parameter.
     *
     * @return the additional parameter
     */
    public Object getParam() {
        return param;
    }

    /**
     * Sets the additional parameter.
     *
     * @param param the new additional parameter
     */
    public void setParam(Object param) {
        this.param = param;
    }

}
