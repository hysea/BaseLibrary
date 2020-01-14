package com.foundao.library.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.foundao.library.base.BaseApp;

public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showToast(String message) {
        Toast.makeText(BaseApp.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@StringRes int resId) {
        showToast(BaseApp.getAppContext().getString(resId));
    }
}
