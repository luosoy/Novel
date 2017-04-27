package com.luosoy.frame.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.ClassEmitter;
import net.sf.cglib.core.CodeEmitter;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.EmitUtils;
import net.sf.cglib.core.Local;
import net.sf.cglib.core.MethodInfo;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.core.TypeUtils;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TypeAwareBeanCopier extends BeanCopier {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeAwareBeanCopier.class);

    /**
     * The Constant BEAN_COPIER.
     */
    private static final Type BEAN_COPIER = TypeUtils.parseType("net.sf.cglib.beans.BeanCopier");

    /**
     * The Constant CONVERTER.
     */
    private static final Type CONVERTER = TypeUtils.parseType("net.sf.cglib.core.Converter");

    /**
     * The Constant COPY.
     */
    private static final Signature COPY = new Signature("copy", Type.VOID_TYPE, new Type[]{Constants.TYPE_OBJECT,
        Constants.TYPE_OBJECT, CONVERTER});

    /**
     * The Constant CONVERT.
     */
    private static final Signature CONVERT = TypeUtils.parseSignature("Object convert(Object, Class, Object)");

    /**
     * Instantiate.
     *
     * @param source the source
     * @param target the target
     * @param useConverter the use converter
     * @return the bean copier
     */
    public static BeanCopier instantiate(Class<?> source, Class<?> target, boolean useConverter) {
        TypeAwareGenerator gen = new TypeAwareGenerator();
        gen.setSource(source);
        gen.setTarget(target);
        gen.setUseConverter(useConverter);
        return gen.create();
    }

    /**
     * Gets the declared method.
     *
     * @param clz the clz
     * @param method the method
     * @param params the params
     * @return the declared method
     */
    private static Method getDeclaredMethod(Class<?> clz, String method, Class<?>... params) {
        try {
            return clz.getDeclaredMethod(method, params);
        } catch (Exception e) {// NOSONAR
            throw new RuntimeException("invalid method: " + method + ", for class: " + clz);// NOSONAR
        }
    }

    /**
     * Gets the declared field.
     *
     * @param clz the clz
     * @param field the field
     * @return the declared field
     */
    private static Field getDeclaredField(Class<?> clz, String field) {
        try {
            return clz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            LOGGER.info(e.getMessage(), e);
            return null;
        } catch (SecurityException e) {
            LOGGER.info(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <p>
     * Project: framework-core</p>
     * <p>
     * Function: The Class TypeAwareGenerator.</p>
     * <p>
     * Description: </p>
     * <p>
     * Company: 税友软件集团股份有限公司</p>
     *
     * @author sbf-dev
     * @version 2.0
     */
    protected static class TypeAwareGenerator extends Generator {

        /**
         * The source.
         */
        private static Field SOURCE = null;

        /**
         * The target.
         */
        private static Field TARGET = null;

        /**
         * The useconverter.
         */
        private static Field USECONVERTER = null;

        /**
         * The get field method.
         */
        private static Method GET_FIELD_METHOD = null;

        /**
         * The get field method sig.
         */
        private static Signature GET_FIELD_METHOD_SIG = null;

        /**
         * The get field method type.
         */
        private static Type GET_FIELD_METHOD_TYPE = null;

        static {
            GET_FIELD_METHOD = getDeclaredMethod(TypeAwareBeanCopier.class, "getField", Class.class, String.class);
            GET_FIELD_METHOD_SIG = new Signature(GET_FIELD_METHOD.getName(), Type.getMethodDescriptor(GET_FIELD_METHOD));
            GET_FIELD_METHOD_TYPE = Type.getType(TypeAwareBeanCopier.class);
            SOURCE = getDeclaredField(Generator.class, "source");
            SOURCE.setAccessible(true);
            TARGET = getDeclaredField(Generator.class, "target");
            TARGET.setAccessible(true);
            USECONVERTER = getDeclaredField(Generator.class, "useConverter");
            USECONVERTER.setAccessible(true);
        }

        /* (non-Javadoc)
         * @see net.sf.cglib.beans.BeanCopier.Generator#generateClass(org.objectweb.asm.ClassVisitor)
         */
        public void generateClass(ClassVisitor v) {
            Class<?> source = null;
            Class<?> target = null;
            Boolean useConverter = null;
            try {
                source = (Class<?>) SOURCE.get(this);
                target = (Class<?>) TARGET.get(this);
                useConverter = (Boolean) USECONVERTER.get(this);
            } catch (Exception e) {
                LOGGER.info(e.getMessage(), e);
            }
            Type sourceType = Type.getType(source);
            Type targetType = Type.getType(target);
            ClassEmitter ce = new ClassEmitter(v);
            ce.begin_class(Constants.V1_2, Constants.ACC_PUBLIC, getClassName(), BEAN_COPIER, null,
                    Constants.SOURCE_FILE);

            EmitUtils.null_constructor(ce);
            CodeEmitter e = ce.begin_method(Constants.ACC_PUBLIC, COPY, null);
            PropertyDescriptor[] getters = ReflectUtils.getBeanGetters(source);
            PropertyDescriptor[] setters = ReflectUtils.getBeanGetters(target);

            Map<Object, Object> names = new HashMap<Object, Object>();
            for (int i = 0; i < getters.length; i++) {
                names.put(getters[i].getName(), getters[i]);
            }
            Local targetLocal = e.make_local();
            Local sourceLocal = e.make_local();
            if (useConverter != null && useConverter) {
                e.load_arg(1);
                e.checkcast(targetType);
                e.store_local(targetLocal);
                e.load_arg(0);
                e.checkcast(sourceType);
                e.store_local(sourceLocal);
            } else {
                e.load_arg(1);
                e.checkcast(targetType);
                e.load_arg(0);
                e.checkcast(sourceType);
            }
            for (int i = 0; i < setters.length; i++) {
                PropertyDescriptor setter = setters[i];
                PropertyDescriptor getter = (PropertyDescriptor) names.get(setter.getName());
                if (getter != null) {
                    MethodInfo read = ReflectUtils.getMethodInfo(getter.getReadMethod());
                    MethodInfo write = ReflectUtils.getMethodInfo(setter.getWriteMethod());
                    if (useConverter) {
                        Type setterType = write.getSignature().getArgumentTypes()[0];
                        e.load_local(targetLocal);
                        e.load_arg(2);
                        e.load_local(sourceLocal);
                        e.invoke(read);
                        e.box(read.getSignature().getReturnType());
                        EmitUtils.load_class(e, setterType);
                        /**
                         * populates the target Field as context param.
                         */
                        EmitUtils.load_class(e, targetType);
                        e.push(setter.getName());
                        e.invoke_static(GET_FIELD_METHOD_TYPE, GET_FIELD_METHOD_SIG);
                        e.invoke_interface(CONVERTER, CONVERT);
                        e.unbox_or_zero(setterType);
                        e.invoke(write);
                    } else if (compatible(getter, setter)) {
                        e.dup2();
                        e.invoke(read);
                        e.invoke(write);
                    }
                }
            }
            e.return_value();
            e.end_method();
            ce.end_class();
        }

        /**
         * Compatible.
         *
         * @param getter the getter
         * @param setter the setter
         * @return true, if successful
         */
        private static boolean compatible(PropertyDescriptor getter, PropertyDescriptor setter) {
            return setter.getPropertyType().isAssignableFrom(getter.getPropertyType());
        }
    }

    /**
     * Gets the field.
     *
     * @param clz the clz
     * @param field the field
     * @return the field
     */
    public static Field getField(Class<?> clz, String field) {
        while (clz != Object.class) {
            Field f = getDeclaredField(clz, field);
            if (f != null) {
                return f;
            }
            clz = clz.getSuperclass();// NOSONAR
        }
        return null;
    }

}
