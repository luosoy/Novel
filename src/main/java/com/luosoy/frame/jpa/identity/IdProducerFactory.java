package com.luosoy.frame.jpa.identity;

import com.luosoy.frame.beans.SpringContextUtil;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class IdProducerFactory {

    /**
     * The producer map.
     */
    private static final Map<Type, IdProducer> PRODUCER_MAP = new HashMap<Type, IdProducer>();

    /**
     * Instantiates a new id producer factory.
     */
    private IdProducerFactory() {
    }

    /**
     * Gets the producer.
     *
     * @param type the type
     * @return the producer
     */
    public static IdProducer getProducer(Class<?> type) {

        IdProducer producer = null;
        if (PRODUCER_MAP.get(type) == null) {
            producer = (IdProducer) SpringContextUtil.getContext().getBean(type);
            PRODUCER_MAP.put(type, producer);
        } else {
            producer = PRODUCER_MAP.get(type);
        }
        return producer;
    }

}
