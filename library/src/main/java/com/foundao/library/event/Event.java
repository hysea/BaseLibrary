package com.foundao.library.event;

/**
 * EventBus发送的事件类
 */
public class Event<T> {
    /**
     * 事件码，在实际使用时，可以通过常量类EventCode来统一管理
     * <p>
     * public class EventCode {
     * public static final int EVENT_A = 1000;
     * public static final int EVENT_B = 1001;
     * }
     * </p>
     */
    private int code;

    /**
     * 事件信息
     */
    private T data;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Event{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
