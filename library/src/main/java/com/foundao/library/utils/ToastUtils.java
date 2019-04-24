package com.foundao.library.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foundao.library.R;
import com.foundao.library.base.BaseApp;

public class ToastUtils {
    /**
     * 全局Toast，防止多次点击重复出现
     */
    private static Toast sToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showToast(CharSequence text) {
//        if (sToast == null) {
//            // 解决小米Toast首次弹出自带应用名称
        sToast = Toast.makeText(BaseApp.getAppContext(), null, Toast.LENGTH_SHORT);
        sToast.setText(text);
//        } else {
//            sToast.setText(text);
//        }
        sToast.show();
    }

    public static void showToast(@StringRes int strId) {
//        if (sToast == null) {
        // 解决小米Toast首次弹出自带应用名称
        sToast = Toast.makeText(BaseApp.getAppContext(), null, Toast.LENGTH_SHORT);
        sToast.setText(strId);
//        } else {
//            sToast.setText(strId);
//        }
        sToast.show();
    }

    public static void dissmissToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
