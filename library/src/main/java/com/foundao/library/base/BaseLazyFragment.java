package com.foundao.library.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Fragment的懒加载
 * create by hysea on 2019/4/10
 */
public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * 判断View是否创建
     */
    protected boolean mIsViewCreated;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisibleToUser;

    protected boolean mIsFirstVisible = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
        if (mIsVisibleToUser) {
            onLazyLoad();
            mIsFirstVisible = false;
            fragmentResume();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        if (!mIsViewCreated) return; // 如果视图未创建就返回
        if (mIsVisibleToUser) {
            fragmentResume();
            if (mIsFirstVisible) {
                onLazyLoad();
                mIsFirstVisible = false;
            }
        } else {
            fragmentPause();
        }
    }

    /**
     * 懒加载数据
     */
    protected abstract void onLazyLoad();


    /**
     * fragment已创建，并且用户可见，该方法调用
     */
    protected void fragmentResume() {

    }

    /**
     * fragment已创建，并且用户不可见，该方法调用
     */
    protected void fragmentPause() {

    }
}
