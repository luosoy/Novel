package com.luosoy.frame.jpa.identity;

import com.luosoy.frame.annotation.AnnotationUtils;
import com.luosoy.frame.exception.SystemException;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.PrePersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Injects identifiers automatically for target entities.
 *
 * @author luozp
 *
 */
public class IdInjectionEntityListener {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IdInjectionEntityListener.class);

    /**
     * Pre persist.
     *
     * @param entity the entity
     */
    @PrePersist
    public void prePersist(Object entity) {
        injectId(entity);
        try {
            List<Field> list = AnnotationUtils.getAnnotationFieldsInClass(IdSequenceGroup.class, entity.getClass());
            for (Field field : list) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value == null) {
                    value = field.getType().newInstance();
                    field.set(entity, value);
                }
                injectId(value);
            }
        } catch (SecurityException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        } catch (IllegalAccessException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        } catch (InstantiationException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        }
    }

    /**
     * Inject id.
     *
     * @param entity the entity
     */
    private void injectId(Object entity) {
        try {
            List<Field> list = AnnotationUtils.getAnnotationFieldsInClass(IdSequenceConsumer.class, entity.getClass());
            for (Field field : list) {
                IdSequenceConsumer consumer = field.getAnnotation(IdSequenceConsumer.class);
                Sequence id = IdUtil.produce(consumer.producerClass(), consumer.param(), entity);
                field.setAccessible(true);
                field.set(entity, id.getSequence());
            }
        } catch (SecurityException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        } catch (IllegalAccessException e) {
            LOGGER.warn("error while injecting id", e);
            throw new SystemException("unable to inject id", e, SystemException.PERSIST_EXCEPTION);
        }
    }

}
