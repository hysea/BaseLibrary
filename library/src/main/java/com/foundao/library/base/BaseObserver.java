package com.foundao.library.base;

import com.foundao.library.R;
import com.foundao.library.http.exception.ApiException;
import com.foundao.library.http.exception.ExceptionHandler;
import com.foundao.library.interfaces.IResponse;
import com.foundao.library.interfaces.IResult;
import com.foundao.library.utils.NetworkUtils;
import com.foundao.library.utils.ResourceUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Observer基类
 * create by hysea on 2019/4/11
 */
public abstract class BaseObserver<T> implements Observer<IResult<T>>, IResponse<T> {

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtils.isConnected(BaseApp.getAppContext())) {
            ApiException ex = new ApiException(ExceptionHandler.NOT_NETWORK_ERROR, ResourceUtils.getString(R.string.not_network));
            onFailure(ex);
            d.dispose();
            return;
        }
        showLoadingProgress();
    }

    @Override
    public void onNext(IResult<T> t) {
        // 防止返回数据为空
        if (t == null) {
            ApiException ex = new ApiException(ExceptionHandler.UN_KNOWN_ERROR, ResourceUtils.getString(R.string.server_error));
            onFailure(ex);
        } else {
            if (t.isSuccess()) {
                onSuccess(t.getHttpData());
            } else {
                ApiException ex = new ApiException(t.getHttCode(), t.getHttpMessage());
                onFailure(ex);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        hideLoadingProgress();
        ApiException ex = ExceptionHandler.handleException(e);
        onFailure(ex);
    }

    @Override
    public void onFailure(ApiException ex) {

    }

    @Override
    public void onComplete() {
        hideLoadingProgress();
    }

    @Override
    public void showLoadingProgress() {
    }

    @Override
    public void hideLoadingProgress() {
    }
}
