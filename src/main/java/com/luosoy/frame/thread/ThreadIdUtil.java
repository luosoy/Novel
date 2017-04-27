package com.luosoy.frame.thread;

import java.util.UUID;

import org.slf4j.MDC;

/**
 * 
 * @author luozp
 */
public final class ThreadIdUtil {

    /**
     * The Constant UUID_NAME.  handler_id
     */
    public static final String UUID_NAME = "sequenceid";

    /**
     * Instantiates a new thread id util.
     */
    private ThreadIdUtil() {
    }

    /**
     * Generate thread uu id.
     */
    public static void generateThreadUUId() {
        UUID uuid = UUID.randomUUID();
        MDC.put(UUID_NAME, uuid.toString().replaceAll("-", ""));
    }

    /**
     * Gets the thread uu id.
     * 
     * @return the thread uu id
     */
    public static String getThreadUUId() {
        return MDC.get(UUID_NAME);
    }

    /**
     * Removes the thread uu id.
     */
    public static void removeThreadUUId() {
        MDC.remove(UUID_NAME);
    }
}
