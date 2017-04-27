/**
 *
 */
package com.luosoy.frame.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

public class SpringContextUtil implements ApplicationContextAware {

    /**
     * The app context.
     */
    private static ApplicationContext APP_CONTEXT;

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APP_CONTEXT = applicationContext;// NOSONAR
    }

    /**
     * Gets the bean.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public static Object getBean(String beanName) {

        if (APP_CONTEXT.containsBean(beanName)) {
            return APP_CONTEXT.getBean(beanName);
        } else {
            return null;
        }
    }

    /**
     * Gets the bean.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @return the bean
     */
    public static <T> T getBean(Class<T> clazz) {

        return APP_CONTEXT.getBean(clazz);
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public static ApplicationContext getContext() {
        return APP_CONTEXT;
    }

    /**
     * Checks if is context initialized.
     *
     * @return true, if is context initialized
     */
    public static boolean isContextInitialized() {
        return APP_CONTEXT != null;
    }

    /**
     * 获取实例.
     *
     * 如果找不到抛出异常。
     *
     * @param <T> the generic type
     * @param name Bean名称
     * @param type Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name);
        Assert.notNull(type);
        return APP_CONTEXT.getBean(name, type);
    }
}
