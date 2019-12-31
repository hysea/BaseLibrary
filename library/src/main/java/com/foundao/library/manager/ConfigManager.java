package com.foundao.library.manager;

import androidx.annotation.IdRes;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数配置管理
 * create by hysea on 2019/4/11
 */
public final class ConfigManager {

    /**
     * BaseUrl地址
     */
    private String mBaseUrl;
    /**
     * Header项配置
     */
    private Map<String, String> mHeaders = new HashMap<>();

    /**
     * 设置图片加载时的占位图
     */
    private int mPlaceholderResId;
    /**
     * 设置图片加载失败的占位图
     */
    private int mErrorResId;

    private static volatile ConfigManager sInstance;

    public static ConfigManager newInstance() {
        if (sInstance == null) {
            synchronized (AppManager.class) {
                if (sInstance == null) {
                    sInstance = new ConfigManager();
                }
            }
        }
        return sInstance;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void addHeaders(Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public void removeHeader(String key) {
        mHeaders.remove(key);
    }

    public int getPlaceholderResId() {
        return mPlaceholderResId;
    }

    public void setPlaceholderResId(@IdRes int mPlaceholderResId) {
        this.mPlaceholderResId = mPlaceholderResId;
    }

    public int getErrorResId() {
        if (mErrorResId == 0) {
            mErrorResId = mPlaceholderResId;
        }
        return mErrorResId;
    }

    public void setErrorResId(@IdRes int mErrorResId) {
        this.mErrorResId = mErrorResId;
    }
}
