package com.foundao.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络工具类
 */
public class NetworkUtils {
    public static final int NETWORK_NONE = 0; // 没有网络连接
    public static final int NETWORK_WIFI = 1; // wifi连接
    public static final int NETWORK_2G = 2; // 2G
    public static final int NETWORK_3G = 3; // 3G
    public static final int NETWORK_4G = 4; // 4G
    public static final int NETWORK_MOBILE = 5; // 手机流量

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    }

    /**
     * 获取活动网络信息
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        if (getConnectivityManager(context) != null) {
            getConnectivityManager(context).getActiveNetworkInfo();
        }
        return null;
    }

    /**
     * 获取当前网络是否是mobile连接
     */
    public static boolean isMobileConnected(Context context) {
        int networkState = getNetworkState(context);
        return networkState == NETWORK_2G || networkState == NETWORK_3G || networkState == NETWORK_4G;
    }

    /**
     * 获取当前网络是否是wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        int networkState = getNetworkState(context);
        return networkState == NETWORK_WIFI;
    }

    /**
     * 获取当前网络连接的类型
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager manager = getConnectivityManager(context);
        if (manager == null) {
            return NETWORK_NONE;
        }
        if (getActiveNetworkInfo(context) == null) {
            return NETWORK_NONE;
        }
        if (!isConnected(context)) {
            return NETWORK_NONE;
        }
        // 判断是否为WIFI
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state) {
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORK_WIFI;
                }
            }
        }
        // 若不是WIFI，则去判断是2G、3G、4G网
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return NETWORK_NONE;
        }
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            // 2G网络
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_2G;
            // 3G网络
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_3G;
            // 4G网络
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_4G;
            default:
                return NETWORK_MOBILE;
        }
    }
}
