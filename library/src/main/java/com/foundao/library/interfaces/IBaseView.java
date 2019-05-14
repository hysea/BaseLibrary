package com.foundao.library.interfaces;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

/**
 * Activity与Fragment的公共逻辑
 * create by hysea on 2019/4/10
 */
public interface IBaseView {

    /**
     * 获取布局id
     */
    @LayoutRes
    int getLayoutId();

    /**
     * 相关初始化操作
     */
    void init(Bundle savedInstanceState);

    /**
     * 是否注册EventBus
     */
    boolean isRegisterEventBus();

    /**
     * 初始化相关事件操作
     */
    void initEvent();

    /**
     * 跳转页面，无参数
     */
    void readyGo(Class<?> clazz);

    /**
     * 跳转页面，带参数
     */
    void readyGo(Class<?> clazz, Bundle bundle);

    /**
     * 跳转页面，无参数，并销毁该界面
     */
    void readyGoThenFinish(Class<?> clazz);

    /**
     * 跳转页面，带参数，并销毁该界面
     */
    void readyGoThenFinish(Class<?> clazz, Bundle bundle);

    /**
     * 跳转页面返回结果，无参数
     */
    void readyGoForResult(Class<?> clazz, int requestCode);

    /**
     * 跳转页面返回结果，带参数
     */
    void readyGoForResult(Class<?> clazz, Bundle bundle, int requestCode);
}
