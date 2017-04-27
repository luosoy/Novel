/*
 * Project Name: kmfex-platform
 * File Name: ConverterService.java
 * Class Name: ConverterService
 *
 * Copyright 2014 KMFEX Inc
 *
 * 
 *
 * http://www.kmfex.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luosoy.frame.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public final class BeanConvertUtil {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConvertUtil.class);

    /**
     * The Constant CACHED_COPIER_MAP.
     */
    private static final Map<String, BeanCopier> CACHED_COPIER_MAP = new ConcurrentHashMap<String, BeanCopier>();

    /**
     * The Constant CACHED_CUSTOM_CONVERTER_MAP.
     */
    private static final Map<String, ObjectConverter> CACHED_CUSTOM_CONVERTER_MAP = new ConcurrentHashMap<String, ObjectConverter>();

    /**
     * Instantiates a new converter util.
     */
    private BeanConvertUtil() {
    }

    /**
     * Convert list.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param target the target
     * @param sourceList the source list
     * @return the list
     */
    public static <T, F> List<F> convertList(Class<T> source, Class<F> target, List<T> sourceList) {
        return convertList(source, target, sourceList, null, null);
    }

    /**
     * Convert list.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param target the target
     * @param sourceList the source list
     * @param converter the converter
     * @param customConverterClass the custom converter class
     * @return the list
     */
    public static <T, F> List<F> convertList(Class<T> source, Class<F> target, List<T> sourceList, Converter converter,// NOSONAR
            Class<? extends ObjectConverter> customConverterClass) {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            @SuppressWarnings("unchecked")
            List<F> targetList = new ArrayList();
            for (T t : sourceList) {
                try {
                    F f = target.newInstance();
                    targetList.add(convert(t, f, converter, customConverterClass));
                } catch (Exception e) {
                    LOGGER.error("When copy instance" + t, e);
                }
            }
            return targetList;
        } else {
            return null;// NOSONAR
        }

    }

    /**
     * Convert.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param target the target
     * @return the f
     */
    public static <T, F> F convert(T source, F target) {
        return convert(source, target, null, null);
    }

    /**
     * Convert.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param target the target
     * @param converter the converter
     * @param customConverterClass the custom converter class
     * @return the f
     */
    public static <T, F> F convert(T source, F target, Converter converter,
            Class<? extends ObjectConverter> customConverterClass) {
        if (source == null || target == null) {
            return null; // NOSONAR
        }
        copy(source, target, converter, customConverterClass);
        return target;
    }

    /**
     * Private methods.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param target the target
     * @param converter the converter
     * @param customConverterClass the custom converter class
     */
    @SuppressWarnings("unchecked")
    private static <T, F> void copy(T source, F target, Converter converter,
            Class<? extends ObjectConverter> customConverterClass) {
        BeanCopier beanCopier = getBeanCopierInstance(source, target.getClass(), converter);
        beanCopier.copy(source, target, converter);
        ObjectConverter customConverter = getCustomConverterInstance(customConverterClass);
        if (customConverter != null) {
            if (target.getClass().getName().endsWith("CMP")) {
                customConverter.convertFromDto(source, target);
            } else if (target.getClass().getName().endsWith("DTO")) {
                customConverter.convertToDto(source, target);
            }
        }
    }

    /**
     * Gets the bean copier instance.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param source the source
     * @param targetClass the target class
     * @param converter the converter
     * @return the bean copier instance
     */
    private static <T, F> BeanCopier getBeanCopierInstance(T source, Class<F> targetClass, Converter converter) {
        String key = source.getClass().getName() + "#" + targetClass.getName();
        BeanCopier beanCopier = CACHED_COPIER_MAP.get(key);
        if (beanCopier == null) {
            synchronized (CACHED_COPIER_MAP) {
                beanCopier = CACHED_COPIER_MAP.get(key);
                if (beanCopier == null) {
                    beanCopier = TypeAwareBeanCopier.instantiate(source.getClass(), targetClass, converter != null);
                    CACHED_COPIER_MAP.put(key, beanCopier);
                }
            }
        }
        return beanCopier;
    }

    /**
     * Gets the custom converter instance.
     *
     * @param <T> the generic type
     * @param <F> the generic type
     * @param customConverterClass the custom converter class
     * @return the custom converter instance
     */
    private static <T, F> ObjectConverter getCustomConverterInstance(
            Class<? extends ObjectConverter> customConverterClass) {
        if (customConverterClass == null) {
            return null;// NOSONAR
        }
        String key = customConverterClass.getName();
        ObjectConverter converter = CACHED_CUSTOM_CONVERTER_MAP.get(key);
        if (converter == null) {
            synchronized (CACHED_CUSTOM_CONVERTER_MAP) {

                converter = (ObjectConverter) SpringContextUtil.getContext().getBean(customConverterClass);
                if (converter == null) {
                    try {
                        converter = customConverterClass.newInstance();
                        CACHED_CUSTOM_CONVERTER_MAP.put(key, converter);
                    } catch (InstantiationException e) {
                        LOGGER.info(e.getMessage(), e);
                        return null;
                    } catch (IllegalAccessException e) {
                        LOGGER.info(e.getMessage(), e);
                        return null;
                    }
                }
            }
        }
        return converter;
    }

}
