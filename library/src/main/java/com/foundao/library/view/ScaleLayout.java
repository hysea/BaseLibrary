package com.foundao.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.foundao.library.R;

/**
 * 按照比例显示的RelativeLayout,主要是以 android:layout_width 的宽度为基
 */
public class ScaleLayout extends RelativeLayout {
    public float mScale = -1;

    public ScaleLayout(Context context) {
        this(context, null);
    }

    public ScaleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScaleLayout);
        mScale = a.getFloat(R.styleable.ScaleLayout_scale, mScale);
        a.recycle();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mScale != -1) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * mScale), MeasureSpec.getMode(widthMeasureSpec)));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}