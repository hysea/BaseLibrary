package com.foundao.library.web;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.foundao.library.utils.ScreenUtils;
import com.foundao.library.utils.ViewUtils;

/**
 * WebView Video
 * create by hysea on 2019/6/3
 */
public class WebViewVideo implements IWebViewVideo {

    private Activity mActivity;
    private WebView mWebView;
    private ViewGroup mParentView;
    private View mVideoView;
    private Window mWindow;
    private WebChromeClient.CustomViewCallback mCallback;

    public WebViewVideo(Activity activity, WebView webView) {
        this.mActivity = activity;
        this.mWebView = webView;
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        if (mActivity == null || mActivity.isFinishing() || mVideoView != null) {
            return;
        }
        this.mVideoView = view;
        this.mCallback = callback;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mWindow = mActivity.getWindow();

        if (mWebView != null) {
            ViewUtils.hide(mWebView);
        }

        if (mParentView == null) {
            FrameLayout decorView = (FrameLayout) mWindow.getDecorView();
            mParentView = new FrameLayout(mActivity);
            mParentView.setBackgroundColor(Color.BLACK);
            decorView.addView(mParentView);
        }
        mParentView.addView(mVideoView);
        ViewUtils.show(mParentView);
    }

    @Override
    public void onHideCustomView() {
        if (mVideoView == null) {
            return;
        }

        if (mActivity != null && !ScreenUtils.isPortrait(mActivity)) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        if (mParentView != null && mVideoView != null) {
            ViewUtils.hide(mVideoView);
            ViewUtils.hide(mParentView);
            mParentView.removeView(mVideoView);
            mVideoView = null;
        }

        if (mCallback != null) {
            mCallback.onCustomViewHidden();
        }

        if (mWebView != null) {
            ViewUtils.show(mWebView);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        if (mWindow == null) {
            return;
        }
        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }
    }
}
