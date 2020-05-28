package com.foundao.library.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foundao.library.interfaces.IBaseView;
import com.foundao.library.view.LoadingView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Fragment基类
 * create by hysea on 2019/4/10
 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    protected Context mContext;
    protected AppCompatActivity mActivity;
    /**
     * 管理Rx的订阅事件
     */
    protected CompositeDisposable mCompositeDisposable;
    public Unbinder mUnBinder;
    private LoadingView mLoadingView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, view);
        if (isRegisteredEventBus()) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
        initEvent();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean isRegisteredEventBus() {
        return false;
    }

    @Override
    public void readyGo(Class<?> clazz) {
        readyGo(clazz, null);
    }

    @Override
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void readyGoThenFinish(Class<?> clazz) {
        readyGoThenFinish(clazz, null);
    }

    @Override
    public void readyGoThenFinish(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        if (mActivity != null) {
            mActivity.finish();
        }
    }

    @Override
    public void readyGoForResult(Class<?> clazz, int requestCode) {
        readyGoForResult(clazz, null, requestCode);
    }

    @Override
    public void readyGoForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = new LoadingView(mContext);
        }
        mLoadingView.show();
    }

    public void dismissLoading() {
        if (isLoading()) {
            mLoadingView.dismiss();
            mLoadingView = null;
        }
    }

    public boolean isLoading() {
        return mLoadingView != null && mLoadingView.isShowing();
    }

    /**
     * 添加订阅
     */
    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅
     */
    protected void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onDestroyView() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (isRegisteredEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        clearDisposable();
        super.onDestroyView();
    }
}
