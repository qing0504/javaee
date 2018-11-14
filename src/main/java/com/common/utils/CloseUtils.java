package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseUtils.class);

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
    }

    public static void close(Object object) {
        if (object instanceof AutoCloseable) {
            close((AutoCloseable) object);
        }
    }

}
