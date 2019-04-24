package com.foundao.library.interfaces;

/**
 * 数据接口
 * create by hysea on 2019/4/11
 */
public interface IResult<T> {

    /**
     * 结果码
     */
    int getHttCode();

    /**
     * 返回消息
     */
    String getHttpMessage();

    /**
     * 返回数据
     */
    T getHttpData();

    /**
     * 数据是否请求成功
     */
    boolean isSuccess();
}
