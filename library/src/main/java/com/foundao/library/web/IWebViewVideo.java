package com.foundao.library.web;

import android.content.res.Configuration;
import android.view.View;
import android.webkit.WebChromeClient;

/**
 * WebView自定义视频View，用于全屏播放
 * create by hysea on 2019/6/3
 */
public interface IWebViewVideo {

    /**
     * 进入全屏模式
     */
    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

    /**
     * 退出全屏模式
     */
    void onHideCustomView();


    /**
     * 屏幕旋转
     */
    void onConfigurationChanged(Configuration config);
}
