package com.foundao.library.view;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 防止Handler内存溢出
 * create by hysea on 2019/5/13
 */
public abstract class LeakHandler<T> extends Handler {

    private WeakReference<T> mReference;

    /**
     * 创建子线程Handler使用的构造器
     */
    public LeakHandler(Looper looper, T reference) {
        super(looper);
        this.mReference = new WeakReference<>(reference);
    }

    /**
     * 创建主线程Handler使用的构造器
     */
    public LeakHandler(T reference) {
        this.mReference = new WeakReference<>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t = mReference.get();
        if (t == null) {
            return;
        }
        handleMessage(t, msg);
    }


    public abstract void handleMessage(T t, Message message);
}
