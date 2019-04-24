package com.foundao.library.download;

/**
 * 下载监听
 * create by hysea on 2019/4/12
 */
public interface DownloadListener {

    /**
     * @param downloadSize 已下载进度
     * @param totalSize    总进度大小
     */
    void onProgress(long downloadSize, long totalSize);

    /**
     * 下载完成
     *
     * @param filePath 文件路径
     */
    void onCompleted(String filePath);

    /**
     * 下载出错
     */
    void onError();
}
