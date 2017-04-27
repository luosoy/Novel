package com.luosoy.frame.jpa.identity;

public final class IdUtil {

    /**
     * Instantiates a new id util.
     */
    private IdUtil() {
    }

    /**
     * Produce.
     *
     * @param producerType the producer type
     * @return the sequence
     */
    public static Sequence produce(Class<?> producerType) {
        return produce(producerType, "", null);
    }

    /**
     * Produce.
     *
     * @param producerType the producer type
     * @param param the param
     * @param entity the entity
     * @return the sequence
     */
    public static Sequence produce(Class<?> producerType, String param, Object entity) {
        IdProducer p = IdProducerFactory.getProducer(producerType);
        return p.produce(new Context(entity, param));
    }

}
