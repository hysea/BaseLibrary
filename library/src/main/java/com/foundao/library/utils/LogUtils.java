package com.foundao.library.utils;

import android.util.Log;

import com.foundao.library.BuildConfig;

public class LogUtils {

    public static final String TAG = LogUtils.class.getSimpleName();


    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }


    public static void v(String msg) {
        Log.v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }


    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String tag, String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, e);
        }
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }
}
