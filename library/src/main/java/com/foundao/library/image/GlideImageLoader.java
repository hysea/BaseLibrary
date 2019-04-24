package com.foundao.library.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.foundao.library.R;

/**
 * Glide工具类
 */
public class GlideImageLoader {
    // 默认占位图
    private static final int PLACE_HOLDER = R.drawable.squre_weijiazai_imagei;
    private static final int ERROR = R.drawable.squre_weijiazai_imagei;

    /**
     * 加载图片
     *
     * @param obj ：图片来源，可以是url，drawable，文件路径等
     */
    public static void loadImage(Context context, Object obj, ImageView image) {
        loadImage(context, obj, image, false);
    }

    /**
     * 加载图片
     *
     * @param obj           ：图片来源，可以是url，drawable，文件路径等
     * @param isPlaceHolder : 是否显示占位图片
     */
    public static void loadImage(Context context, Object obj, ImageView image, boolean isPlaceHolder) {
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (isPlaceHolder) {
            Glide.with(context).load(obj).apply(requestOptions(PLACE_HOLDER, ERROR)).into(image);
        } else {
            Glide.with(context).load(obj).apply( new RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)).into(image);
        }
    }

    /**
     * 加载图片，且自定义占位图片
     *
     * @param obj ：图片来源，可以是url，drawable，文件路径等
     */
    public static void loadImage(Context context, Object obj, ImageView image, RequestOptions options) {
        Glide.with(context).load(obj).apply(options.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)).into(image);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(Context context, Object obj, ImageView image) {
        Glide.with(context).load(obj).apply(circleRequestOptions(PLACE_HOLDER, ERROR)).into(image);
    }

    /**
     * 占位图片与错误图片
     */
    public static RequestOptions requestOptions(int placeholderResId, int errorResId) {
        return new RequestOptions()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(placeholderResId)
                .error(errorResId);
    }

    /**
     * 圆形图片处理
     */
    public static RequestOptions circleRequestOptions(int placeholderResId, int errorResId) {
        return requestOptions(placeholderResId, errorResId)
                .transform(new GlideCircleTransformation());
    }
}
