/**
 * 
 */
package com.luosoy.frame.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AnnotationUtils {

    /**
     * The Constant ANNOTATION_FIELD_CACHE_MAP.
     */
    private static final Map<Class<?>, Map<Class<?>, List<Field>>> ANNOTATION_FIELD_CACHE_MAP = new ConcurrentHashMap<Class<?>, Map<Class<?>, List<Field>>>();

    /**
     * Instantiates a new annotation utils.
     */
    private AnnotationUtils() {
    }

    /**
     * Gets the annotation fields in class.
     * 
     * @param annotation
     *            the annotation
     * @param targetClass
     *            the target class
     * @return the annotation fields in class
     */
    public static List<Field> getAnnotationFieldsInClass(Class<? extends Annotation> annotation, Class<?> targetClass) {

        if (ANNOTATION_FIELD_CACHE_MAP.get(targetClass) != null) {
            if (ANNOTATION_FIELD_CACHE_MAP.get(targetClass).get(annotation) != null) {
                return ANNOTATION_FIELD_CACHE_MAP.get(targetClass).get(annotation);
            }
        } else {
            Map<Class<?>, List<Field>> annotationMap = new HashMap<Class<?>, List<Field>>();
            ANNOTATION_FIELD_CACHE_MAP.put(targetClass, annotationMap);
        }
        List<Field> annotationFieldList = new ArrayList<Field>();
        findAnnotationField(annotationFieldList, annotation, targetClass);
        ANNOTATION_FIELD_CACHE_MAP.get(targetClass).put(annotation, annotationFieldList);
        return annotationFieldList;
    }

    /**
     * Find annotation field.
     * 
     * @param resultList
     *            the result list
     * @param annotation
     *            the annotation
     * @param targetClass
     *            the target class
     */
    private static void findAnnotationField(List<Field> resultList, Class<? extends Annotation> annotation,
            Class<?> targetClass) {

        for (Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                resultList.add(field);
            }
        }

        if (targetClass.getSuperclass() != null && targetClass.getSuperclass() != Object.class) {
            findAnnotationField(resultList, annotation, targetClass.getSuperclass());
        }
    }

    /**
     * Find immediate annotation methods in class.
     * 
     * @param annotation
     *            the annotation
     * @param targetClass
     *            the target class
     * @return the list
     */
    public static List<Method> findImmediateAnnotationMethodsInClass(Class<? extends Annotation> annotation,
            Class<?> targetClass) {

        List<Method> result = new ArrayList<Method>();
        for (Method method : targetClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                result.add(method);
            }
        }
        return result;
    }
}
