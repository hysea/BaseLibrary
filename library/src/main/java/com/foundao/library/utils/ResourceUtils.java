package com.foundao.library.utils;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.foundao.library.base.BaseApp;

/**
 * 资源工具类
 * create by hysea on 2019/4/11
 */
public class ResourceUtils {

    public static String getString(@StringRes int strId) {
        return BaseApp.getAppContext().getString(strId);
    }

    public static int getColor(@ColorRes int colorId) {
        return ContextCompat.getColor(BaseApp.getAppContext(), colorId);
    }
}
