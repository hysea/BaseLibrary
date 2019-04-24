package com.foundao.library.http.exception;

/**
 * 自定义服务器错误
 * Created by hysea on 2018/4/4.
 */
public class ServerException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
