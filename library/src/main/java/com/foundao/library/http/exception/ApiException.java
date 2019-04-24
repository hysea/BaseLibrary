package com.foundao.library.http.exception;

/**
 * api接口错误/异常统一处理
 * 异常=[程序异常、网络异常、解析异常...]
 * 错误=[接口逻辑错误，如-1001:token失效]
 * Created by hysea on 2018/4/4.
 */
public class ApiException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
