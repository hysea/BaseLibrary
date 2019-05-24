package com.foundao.library.recycler;

import java.util.List;

/**
 * 主要针对列表分页处理
 * create by hysea on 2019/4/26
 */
public class PageHelper<T> {

    /**
     * 第一页从1开始
     */
    public static final int FIRST_PAGE = 1;

    /**
     * 默认每页显示10条
     */
    private static final int DEFAULT_MAX_PAGE_SIZE = 10;

    private int mCurrentPage = FIRST_PAGE;
    private int mMaxPageSize = DEFAULT_MAX_PAGE_SIZE;

    private PageListener<T> mPageListener;

    public PageHelper(PageListener<T> listener) {
        this.mPageListener = listener;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
    }


    public int getMaxPageSize() {
        return mMaxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.mMaxPageSize = maxPageSize;
    }

    /**
     * 数据处理，分类
     */
    public void handleResult(List<T> result) {
        if (result == null || (mCurrentPage == FIRST_PAGE && result.isEmpty())) {
            mPageListener.onEmptyData();
        } else if (mCurrentPage != FIRST_PAGE && result.isEmpty()) {
            mPageListener.onNextEmptyData();
        } else {
            if (mCurrentPage == FIRST_PAGE) {
                mPageListener.onFirstPage(result);
            } else {
                mPageListener.onNextPage(result);
            }
        }
    }

    /**
     * 加载下一页
     */
    public void nextPage() {
        mCurrentPage++;
    }


    public interface PageListener<T> {
        /**
         * 第一页数据
         */
        void onFirstPage(List<T> data);

        /**
         * 下一页数据
         */
        void onNextPage(List<T> data);

        /**
         * 空数据
         */
        void onEmptyData();


        /**
         * 下一页无数据，表示已加载完毕
         */
        void onNextEmptyData();
    }
}
