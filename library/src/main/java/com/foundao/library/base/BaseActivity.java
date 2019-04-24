package com.foundao.library.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.foundao.library.R;
import com.foundao.library.interfaces.IBaseView;
import com.foundao.library.manager.AppManager;
import com.foundao.library.view.LoadingView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * create by hysea on 2019/4/10
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    protected Context mContext;

    private Unbinder mUnBinder;

    protected static String TAG;

    protected LoadingView mLoadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setContentView(getLayoutId());
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        AppManager.getAppManager().addActivity(this);
        init(savedInstanceState);
        initEvent();
    }

    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = new LoadingView(this);
        }
        if (!mLoadingView.isShowing()) {
            mLoadingView.show();
        }
    }

    public void dismissLoading() {
        if (mLoadingView != null && mLoadingView.isShowing()) {
            mLoadingView.dismiss();
            mLoadingView = null;
        }
    }

    public boolean isLoading() {
        return mLoadingView != null && mLoadingView.isShowing();
    }

    @Override
    public boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void readyGo(Class<?> clazz) {
        readyGo(clazz, null);
    }

    @Override
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
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
        finish();
    }

    @Override
    public void readyGoForResult(Class<?> clazz, int requestCode) {
        readyGoForResult(clazz, null, requestCode);
    }

    @Override
    public void readyGoForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().removeActivity(this);
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
