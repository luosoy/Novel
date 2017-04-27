/**
 *
 */
package com.luosoy.frame.beans.converter;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConverterUtilsWrapper {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterUtilsWrapper.class);

    /**
     * The Constant BEANUTILS_BEANS.
     */
    private static final BeanUtilsBean BEANUTILS_BEANS = new BeanUtilsBean2();

    static {
        BeanUtilsBean.setInstance(BEANUTILS_BEANS);
        BEANUTILS_BEANS.getConvertUtils().register(new DateCustomConverter(), Date.class);
        BEANUTILS_BEANS.getConvertUtils().register(new BigDicimalConverter(true), java.math.BigDecimal.class);
    }

    /**
     * Instantiates a new converter utils wrapper.
     */
    private ConverterUtilsWrapper() {
    }

    /**
     * Register.
     *
     * @param converter the converter
     * @param type the type
     */
    public static void register(Converter converter, Class<?> type) {
        BEANUTILS_BEANS.getConvertUtils().register(converter, type);
    }

    /**
     * Convert.
     *
     * @param value the value
     * @param clazz the clazz
     * @return the object
     */
    public static Object convert(String value, Class<?> clazz) {
        if (value != null) {
            return BEANUTILS_BEANS.getConvertUtils().convert(value, clazz);
        }
        return value;
    }

    /**
     * Convert.
     *
     * @param value the value
     * @param clazz the clazz
     * @return the object
     */
    public static Object convert(Object value, Class<?> clazz) {
        if (value != null) {
            return BEANUTILS_BEANS.getConvertUtils().convert(value, clazz);
        }
        return value;
    }

    /**
     * Convert.
     *
     * @param values the values
     * @param clazz the clazz
     * @return the object
     */
    public static Object convert(String[] values, Class<?> clazz) {
        if (values != null) {
            return BEANUTILS_BEANS.getConvertUtils().convert(values, clazz);
        }
        return values;
    }
}
