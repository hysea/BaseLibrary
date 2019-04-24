package com.foundao.library.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.foundao.library.R;
import com.foundao.library.utils.AnimationsContainer;
import com.foundao.library.utils.ScreenUtils;

/**
 * 加载动画
 */
public class LoadingView extends Dialog {
    private AnimationsContainer.FramesSequenceAnimation mAnimation;

    public LoadingView(Context context) {
        this(context, R.style.LoadingDialog);
    }

    public LoadingView(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading_view);
        init(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight(getContext()), Gravity.CENTER);
    }

    private void init(float width, float height, int gravity) {
        Window window = this.getWindow();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        if (width == 0.0F) {
            params.width = -2;
        } else if (width > 0.0F && width <= 1.0F) {
            params.width = (int) ((float) this.getContext().getResources().getDisplayMetrics().widthPixels * width);
        } else {
            params.width = (int) width;
        }

        if (height == 0.0F) {
            params.height = -2;
        } else if (height > 0.0F && height <= 1.0F) {
            params.height = (int) ((float) this.getContext().getResources().getDisplayMetrics().heightPixels * height);
        } else {
            params.height = (int) height;
        }

        params.verticalMargin = -0.1F;
        window.setAttributes(params);
        window.setDimAmount(0.2f);
        this.getWindow().setGravity(gravity);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        startAnim();
    }

    private void startAnim() {
        ImageView loading = findViewById(R.id.loading);
        mAnimation = AnimationsContainer.getInstance().createAnim(loading, R.array.loading_view_xinhua, 10);
    }

    @Override
    public void show() {
        super.show();
        if (mAnimation != null) {
            mAnimation.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mAnimation != null) {
            mAnimation.stop();
        }
    }
}
