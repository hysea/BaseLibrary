package com.foundao.library.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.foundao.library.R;

/**
 * 加载动画
 */
public class LoadingView {
    private final Context context;

    private ViewGroup root;
    private ViewGroup loadingRoot;
    private ProgressBar loading;

    public LoadingView(Context context) {
        this.context = context;
        if (!(context instanceof Activity)) {
            return;
        }
        root = ((Activity) context).findViewById(android.R.id.content);
    }

    public void show() {
        if (root == null) {
            return;
        }
        if (loading == null) {
            LayoutInflater.from(context).inflate(R.layout.layout_loading_view, root);
            loadingRoot = root.findViewById(R.id.loadingRoot);
            loading = loadingRoot.findViewById(R.id.loading);
        }
        loadingRoot.setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        if (loadingRoot != null) {
            loadingRoot.setVisibility(View.GONE);
            root.removeView(loadingRoot);
        }
    }

    public boolean isShowing() {
        if (loadingRoot == null) {
            return false;
        }
        return loadingRoot.getVisibility() == View.VISIBLE;
    }
}
