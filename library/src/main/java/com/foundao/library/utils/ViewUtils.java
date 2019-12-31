package com.foundao.library.utils;

import android.view.MotionEvent;
import android.view.View;

/**
 * View相关工具类
 * create by hysea on 2019/5/9
 */
public class ViewUtils {

    /**
     * 默认两次点击间隔时间
     */
    private static final int DEFAULT_DELAY_TIME = 800;
    private static long mLastClickTime;

    private ViewUtils() {
        throw new IllegalArgumentException("ViewUtils cannot be instantiated");
    }

    /**
     * View是否可见
     */
    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    /**
     * 设置View的显示状态
     */
    public static void setViewShowState(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }


    /**
     * 显示View
     */
    public static void show(View view) {
        if (!isVisible(view)) {
            setViewShowState(view, View.VISIBLE);
        }
    }

    /**
     * 隐藏View
     */
    public static void hide(View view) {
        if (isVisible(view)) {
            setViewShowState(view, View.GONE);
        }
    }

    public static boolean isFastClick() {
        return isFastClick(DEFAULT_DELAY_TIME);
    }

    /**
     * 用于判断是否快速点击
     * @param delayTime 两次点击间隔时间
     */
    public static boolean isFastClick(long delayTime) {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - mLastClickTime) >= DEFAULT_DELAY_TIME) {
            flag = false;
        }
        mLastClickTime = currentClickTime;
        return flag;
    }

    /**
     * 判断是否在控件区域内
     */
    public static boolean inRangeOfView(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (event.getX() < x || event.getX() > (x + view.getMeasuredWidth()) ||
                event.getY() < y || event.getY() > (y + view.getMeasuredHeight())) {
            return false;
        }
        return true;
    }
}
