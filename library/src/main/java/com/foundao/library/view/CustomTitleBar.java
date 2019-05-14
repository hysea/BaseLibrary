package com.foundao.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foundao.library.R;

import io.reactivex.annotations.NonNull;

/**
 * 通用标题栏
 */
public class CustomTitleBar extends RelativeLayout implements View.OnClickListener {

    /**
     * 标题栏的根布局
     */
    private RelativeLayout mRelativeLayout;
    /**
     * 标题栏的左边按返回图标
     */
    private ImageView mIvLeft;
    /**
     * 标题栏的右边图标
     */
    private ImageView mIvRight;
    /**
     * 标题栏左边按钮文字
     */
    private TextView mTvLeft;

    /**
     * 右边按钮的文字颜色
     */
    private int mLeftButtonTextColor;
    /**
     * 右边保存按钮的文字大小
     */
    private int mLeftButtonTextSize;
    /**
     * 标题栏文字标题
     */
    private TextView mTvTitle;
    /**
     * 标题栏的背景颜色
     */
    private int mTitleBackgroundColor;
    /**
     * 标题栏的显示的标题文字
     */
    private String mTitleText;
    /**
     * 标题栏的显示的标题文字颜色
     */
    private int mTitleTextColor;
    /**
     * 标题栏的显示的标题文字大小
     */
    private int mTitleTextSize;

    /**
     * 标题栏的顶部分割线
     */
    private View line;
    /**
     * 标题栏的右边按钮文字
     */
    private TextView mTvRight;
    /**
     * 右边保存按钮的资源图片
     */
    private int mRightButtonImageId;
    /**
     * 右边保存按钮的文字
     */
    private String mRightButtonText;
    /**
     * 右边按钮的文字颜色
     */
    private int mRightButtonTextColor;
    /**
     * 右边保存按钮的文字大小
     */
    private int mRightButtonTextSize;

    /**
     * 返回按钮上显示的文字
     */
    private String mLeftButtonText;
    /**
     * 返回按钮的资源图片
     */
    private int mLeftButtonImageId;

    /**
     * 是否显示分割线
     */
    private boolean mIsShowLine;


    private TitleBarOnClickListener mTitleBarOnClickListener;

    public CustomTitleBar(Context context) {
        this(context, null);
    }

    public CustomTitleBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.actionbar, this);
        mRelativeLayout = findViewById(R.id.relay_background);
        mIvLeft = findViewById(R.id.iv_left);
        mTvLeft = findViewById(R.id.tv_left);
        mTvTitle = findViewById(R.id.tv_title);
        mTvRight = findViewById(R.id.tv_right);
        mIvRight = findViewById(R.id.iv_right);
        line = findViewById(R.id.line);

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font_arialmt));
        setTextTypeface(typeFace);
    }


    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        mLeftButtonImageId = typedArray.getResourceId(R.styleable.CustomTitleBar_left_button_image, 0);
        mLeftButtonText = typedArray.getString(R.styleable.CustomTitleBar_left_button_text);
        mLeftButtonTextColor = typedArray.getColor(R.styleable.CustomTitleBar_left_button_textColor, Color.GRAY);
        mLeftButtonTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_left_button_textSize, sp2px(context, 14));

        mTitleBackgroundColor = typedArray.getColor(R.styleable.CustomTitleBar_title_background_color, Color.WHITE);
        mTitleText = typedArray.getString(R.styleable.CustomTitleBar_title_text);
        mTitleTextColor = typedArray.getColor(R.styleable.CustomTitleBar_title_textColor, Color.GRAY);
        mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_title_textSize, sp2px(context, 14));

        mRightButtonImageId = typedArray.getResourceId(R.styleable.CustomTitleBar_right_button_image, 0);
        mRightButtonText = typedArray.getString(R.styleable.CustomTitleBar_right_button_text);
        mRightButtonTextColor = typedArray.getColor(R.styleable.CustomTitleBar_right_button_textColor, Color.GRAY);
        mRightButtonTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_right_button_textSize, sp2px(context, 14));

        mIsShowLine = typedArray.getBoolean(R.styleable.CustomTitleBar_show_line, true);
        line.setVisibility(mIsShowLine ? VISIBLE : GONE);

        setTitleBarBackground(mTitleBackgroundColor);
        setTitle(mTitleText);
        setTitleTextSize(mTitleTextSize);
        setTitleTextColor(mTitleTextColor);

        setLeftIcon(mLeftButtonImageId);
        setTvLeft(mLeftButtonText);
        setTvLeftTextSize(mLeftButtonTextSize);
        setTvLeftTextColor(mLeftButtonTextColor);

        setRightIcon(mRightButtonImageId);
        setTvRight(mRightButtonText);
        setTvRightTextColor(mRightButtonTextColor);
        setTvRightTextSize(mRightButtonTextSize);

        typedArray.recycle();
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(GONE);
        } else {
            mTvTitle.setText(title);
            mTvTitle.setVisibility(VISIBLE);
        }
    }

    public void setTitleTextSize(int textSize) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setTitleTextColor(int textColor) {
        mTvTitle.setTextColor(textColor);
    }

    public void setTvLeft(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvLeft.setVisibility(GONE);
        } else {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(text);
        }
    }

    public void setTvLeftTextSize(int textSize) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setTvLeftTextColor(int textColor) {
        mTvLeft.setTextColor(textColor);
    }

    public void setTvRight(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvRight.setVisibility(GONE);
        } else {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(text);
        }
    }


    public void setTvRightTextSize(int textSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }


    public void setTvRightTextColor(int textColor) {
        mTvRight.setTextColor(textColor);
    }

    /**
     * 设置文本的字体
     */
    public void setTextTypeface(@NonNull Typeface typeface) {
        mTvLeft.setTypeface(typeface);
        mTvTitle.setTypeface(typeface);
        mTvRight.setTypeface(typeface);
    }

    public void setLeftIcon(int resId) {
        if (resId == 0) {
            mIvLeft.setVisibility(View.GONE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(resId);
        }
    }

    public void setRightIcon(int resId) {
        if (resId == 0) {
            mIvRight.setVisibility(View.GONE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(resId);
        }
    }

    public void setAction(TitleBarOnClickListener listener) {
        mIvLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
        mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mTitleBarOnClickListener = listener;
    }


    public void setLineIsVisible(int visibility) {
        line.setVisibility(visibility);
    }


    public void setShowRightButton(boolean showRightButton) {
        mTvRight.setVisibility(showRightButton ? VISIBLE : INVISIBLE);
        mIvRight.setVisibility(showRightButton ? VISIBLE : INVISIBLE);
    }

    public void setShowLeftButton(boolean showLeftButton) {
        mTvLeft.setVisibility(showLeftButton ? VISIBLE : INVISIBLE);
        mIvLeft.setVisibility(showLeftButton ? VISIBLE : INVISIBLE);
    }


    public void setTitleBarBackground(int resId) {
        mRelativeLayout.setBackgroundColor(resId);
    }


    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public interface TitleBarOnClickListener {
        void performAction(View view);
    }

    @Override
    public void onClick(View v) {
        mTitleBarOnClickListener.performAction(v);
    }
}
