package com.foundao.library.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭相关工具类
 * Created by hysea on 2018/5/2.
 */
public class CloseUtils {
    private CloseUtils() {
        throw new IllegalArgumentException("cannot be instantiated");
    }

    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null || closeables.length <= 0) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 静默关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIOQuietly(final Closeable... closeables) {
        if (closeables == null || closeables.length <= 0) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
