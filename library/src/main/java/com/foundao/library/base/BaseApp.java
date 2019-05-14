package com.foundao.library.base;

import android.app.Application;

import com.foundao.library.BuildConfig;
import com.foundao.library.utils.LogUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.mmkv.MMKV;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * 基类App
 */
public class BaseApp extends Application {
    private static BaseApp sInstance;

    public static BaseApp getAppContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        String dir = MMKV.initialize(this);
        LogUtils.i("MMKV dir : " + dir);
        initSwipeBack();
        initLeakcanary();
        FileDownloader.init(getApplicationContext());
    }

    /**
     * 如果要在项目中使用BGASwipeBackHelper，必须在 Application 的 onCreate 初始化
     */
    public void initSwipeBack() {
        // 第一个参数：应用程序上下文
        // 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
        BGASwipeBackHelper.init(this, null);
    }

    protected void initLeakcanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 初始化Bugly
     */
    protected void initBugly(String appId) {
        Bugly.init(getApplicationContext(), appId, BuildConfig.DEBUG);
    }

}
