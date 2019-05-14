package com.foundao.library.video;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.foundao.library.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/**
 * 视频播放器
 * create by hysea on 2019/4/15
 */
public class VideoPlayer extends StandardGSYVideoPlayer {

    private Context mContext;
    protected ImageView mCoverImage;
    private String mCoverUrl;

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
        initConfig();
        initEvent();
    }

    /**
     * 初始化配置参数
     */
    protected void initConfig() {
        // 取消全屏动画
        this.setShowFullAnimation(false);
        // 是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        this.setAutoFullWithSize(true);
        // 暂停时，显示封面，防止黑屏
        this.setShowPauseCover(true);

        if (mThumbImageViewLayout != null &&
                (mCurrentState == -1 || mCurrentState == CURRENT_STATE_NORMAL || mCurrentState == CURRENT_STATE_ERROR)) {
            mThumbImageViewLayout.setVisibility(VISIBLE);
        }
    }

    /**
     * 初始化事件
     */
    protected void initEvent() {
        // 全屏点击处理
        mFullscreenButton.setOnClickListener(v -> {
            if (mOrientationUtils != null) {
                mOrientationUtils.resolveByClick();
            }
            startWindowFullscreen(mContext, true, true);
        });
    }

    @Override
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
        VideoPlayer videoPlayer = (VideoPlayer) gsyBaseVideoPlayer;
        videoPlayer.loadCoverImage(mCoverUrl);
        return gsyBaseVideoPlayer;
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
     * 设置返回按钮是否隐藏
     */
    public void setBackButtonVisible(boolean visible) {
        this.mBackButton.setVisibility(visible ? VISIBLE : GONE);
    }

    public ImageView getCoverImage() {
        return mCoverImage;
    }

    /**
     * 加载封面图
     */
    public void loadCoverImage(String url) {
        this.mCoverUrl = url;
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
