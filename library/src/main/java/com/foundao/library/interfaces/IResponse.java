package com.foundao.library.interfaces;

import com.foundao.library.http.exception.ApiException;

/**
 * 响应回调接口
 * create by hysea on 2019/4/11
 */
public interface IResponse<T> {
    /**
     * 显示加载进度
     */
    void showLoadingProgress();

    /**
     * 隐藏加载进度
     */
    void hideLoadingProgress();

    /**
     * 请求成功
     */
    void onSuccess(T t);

    /**
     * 请求失败
     */
    void onFailure(ApiException ex);
}
