package com.foundao.library.download;

import com.foundao.library.utils.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

/**
 * 下载帮助类
 * create by hysea on 2019/4/12
 */
public class DownloadHelper {
    private static final String TAG = DownloadHelper.class.getSimpleName();

    /**
     * 下载文件
     *
     * @param dir         保存目录
     * @param fileName    文件名
     * @param downloadUrl 下载地址
     */
    public static void download(String dir, String fileName, String downloadUrl) {
        String filePath = dir + File.separator + fileName;
        download(filePath, downloadUrl);
    }

    /**
     * 下载文件
     *
     * @param filePath    文件存储路径
     * @param downloadUrl 下载地址
     */
    public static void download(String filePath, String downloadUrl) {
        DownloadListener listener = null;
        download(filePath, downloadUrl, listener);
    }

    /**
     * 下载文件
     *
     * @param dir         文件存储目录
     * @param fileName    文件存储名字
     * @param downloadUrl 下载地址
     * @param listener    下载进度监听
     */
    public static void download(String dir, String fileName, String downloadUrl, final DownloadListener listener) {
        String filePath = dir + File.separator + fileName;
        download(filePath, downloadUrl, listener);
    }

    /**
     * 下载文件
     *
     * @param filePath    文件存储路径
     * @param downloadUrl 下载地址
     * @param listener    下载进度监听
     */
    public static void download(String filePath, String downloadUrl, final DownloadListener listener) {
        LogUtils.i(TAG, "文件开始下载，下载地址：" + downloadUrl + ", 存储地址：" + filePath);
        FileDownloader.getImpl()
                .create(downloadUrl)
                .setPath(filePath)
                .setForceReDownload(true)
                .setListener(new FileDownloadSampleListener() {
                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                        if (listener != null) {
                            listener.onProgress(soFarBytes, totalBytes);
                        }
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        LogUtils.i(TAG, "文件下载完成，文件路径为：" + task.getTargetFilePath());
                        if (listener != null) {
                            listener.onCompleted(task.getTargetFilePath());
                        }
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        LogUtils.i(TAG, "文件下载出错：" + e.getMessage());
                        if (listener != null) {
                            listener.onError();
                        }
                    }
                })
                .start();
    }

    /**
     * 停止下载
     */
    public static void stopDownload() {
        FileDownloader.getImpl().pauseAll();
    }
}
