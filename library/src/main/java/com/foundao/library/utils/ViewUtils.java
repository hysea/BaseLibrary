package com.foundao.library.utils;

import android.view.MotionEvent;
import android.view.View;

/**
 * View相关工具类
 * create by hysea on 2019/5/9
 */
public class ViewUtils {

    private ViewUtils() {
        throw new IllegalArgumentException("ViewUtils cannot be instantiated");
    }

    /**
     * 判断是否在控件区域内
     */
    public static boolean inRangeOfView(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (event.getX() < x || event.getX() > (x + view.getMeasuredWidth()) || event.getY() < y || event.getY() > (y + view.getMeasuredHeight())) {
            return false;
        }
        return true;
    }
}
