package com.foundao.library.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件相关工具类
 */
public class FileUtils {

    private FileUtils() {
        throw new IllegalArgumentException("FileUtils cannot be instantiated");
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isFileExists(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return isFileExists(file);
    }

    /**
     * 判断文件是否存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 删除单个文件文件
     */
    public static boolean deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        return deleteFile(getFileByPath(filePath));
    }

    /**
     * 删除单个文件文件
     */
    public static boolean deleteFile(File file) {
        if (file == null) return false;
        if (!file.exists()) return true;

        if (file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }

    /**
     * 删除目录
     */
    public static boolean deleteDir(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return false;
        }
        return deleteDir(getFileByPath(dirPath));
    }

    /**
     * 采用递归方式删除目录
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) return false;
        if (!dir.exists()) return true; // 如果目录不存在
        if (!dir.isDirectory()) return false;

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (!deleteFile(file)) return false;
            } else if (file.isDirectory()) {
                if (!deleteDir(file)) return false;
            }
        }
        return dir.delete();

    }

    /**
     * 根据文件路径获取文件
     */
    public static File getFileByPath(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    /**
     * 判断SD卡是否可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取缓存目录
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (isSDCardEnable()) {
            // : /storage/emulated/0/Android/data/packagename/cache
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            // : /data/data/packagename/cache
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取文件存储目录
     */
    public static String getDiskFileDir(Context context, String dir) {
        String filePath = null;
        if (isSDCardEnable()) {
            filePath = context.getExternalFilesDir(dir).getAbsolutePath();
        } else {
            filePath = context.getFilesDir().getAbsolutePath() + File.separator + dir;
        }

        File file = new File(filePath);
        if (!file.exists()) { // 如果该目录不存在就创建该目录
            file.mkdirs();
        }
        return filePath;
    }

    public static String streamToStr(InputStream is) {
        return streamToStr(is, "UTF-8");
    }

    /**
     * 将InputStream转换成String
     *
     * @param charset 字符集
     */
    public static String streamToStr(InputStream is, String charset) {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(is, charset);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(isr, br, is);
        }
        return builder.toString();
    }

    /**
     * 将file转换为uri
     */
    public static Uri fromFileToUri(Context context, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = context.getPackageName() + ".provider";
            fileUri = FileProvider.getUriForFile(context, authority, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    /**
     * 将Uri转换成path
     */
    public static String getFilePathFromContentUri(Context context, Uri uri) {
        String filePath = "";
        if (uri == null) {
            return filePath;
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] filePathColumn = {MediaStore.MediaColumns.DATA};
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
                cursor.close();
            }
        }
        return filePath;
    }

    /**
     * 通过Url获取文件名称
     */
    public static String getFileNameByUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
