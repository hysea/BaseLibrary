package com.foundao.library.video;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.foundao.library.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * 视频播放器
 * create by hysea on 2019/4/15
 */
public class VideoPlayer extends StandardGSYVideoPlayer {

    private Context mContext;
    protected ImageView mCoverImage;

    public VideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        this.mContext = context;

        mCoverImage = findViewById(R.id.thumb_image);
    }

    @Override
    public int getLayoutId() {
        if (mIfCurrentIsFullscreen) {
            return getFullLayoutId();
        } else {
            return getNormalLayoutId();
        }
    }

    /**
     * 获取普通视屏布局
     */
    protected @LayoutRes
    int getNormalLayoutId() {
        return R.layout.layout_video_normal;
    }

    /**
     * 获取全屏视频布局
     */
    protected @LayoutRes
    int getFullLayoutId() {
        return R.layout.layout_video_land;
    }


    /**
     * 设置标题是否可见
     */
    public void setTitleVisible(boolean visible) {
        this.mTitleTextView.setVisibility(visible ? VISIBLE : GONE);
    }


    /**
     * 加载封面图
     */
    public void loadCoverImage(String url) {
        Glide.with(mContext).load(url).into(mCoverImage);
    }

    /**
     * 加载封面图
     *
     * @param url              封面图地址
     * @param placeholderResId 占位图片
     */
    public void loadCoverImage(String url, int placeholderResId) {
        Glide.with(mContext)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .centerCrop()
                                .error(placeholderResId)
                                .placeholder(placeholderResId))
                .load(url)
                .into(mCoverImage);
    }
}
