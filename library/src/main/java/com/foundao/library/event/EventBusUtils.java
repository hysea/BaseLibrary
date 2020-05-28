package com.foundao.library.event;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus工具类
 */
public class EventBusUtils {

    private EventBusUtils() {}

    /**
     * 注册EventBus
     */
    public static void register(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber);
        }
    }

    /**
     * 取消EventBus注册
     */
    public static void unregister(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }
    }

    /**
     * 发送普通事件
     */
    public static <T> void post(Event<T> event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     */
    public static <T> void postSticky(Event<T> event) {
        EventBus.getDefault().postSticky(event);
    }
}
