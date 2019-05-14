package com.foundao.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 屏幕工具类
 *
 * @create @author hysea on 2019/5/14
 */
public class ScreenUtils {

    private ScreenUtils() {
        throw new IllegalArgumentException("ScreenUtils cannot be instantiated");
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    /**
     * 获取屏幕尺寸，包含宽和高
     */
    public static int[] getScreenSize(Context context) {
        int[] size = new int[2];
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        size[0] = metrics.widthPixels;
        size[1] = metrics.heightPixels;
        return size;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        try {
            int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = Resources.getSystem().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
