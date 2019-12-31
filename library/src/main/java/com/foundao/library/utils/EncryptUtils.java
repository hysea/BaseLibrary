package com.foundao.library.utils;

import androidx.annotation.NonNull;

import com.foundao.library.constant.EncryptType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @create @author hysea on 2019/5/13
 */
public class EncryptUtils {

    private EncryptUtils() {
        throw new IllegalArgumentException("EncryptUtils cannot be instantiated");
    }

    /**
     * MD5加密字符串
     *
     * @return 返回32位的小写加密串
     */
    public static String md5Encrypt(String text) {
        return encrypt(text, EncryptType.MD5);
    }

    /**
     * MD5文件加密
     */
    public static byte[] md5Encrypt(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance(EncryptType.MD5.getValue());
            md.update(buffer);
            return md.digest();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(fis);
        }
        return null;
    }


    /**
     * SHA-256加密字符串
     */
    public static String sha256Encrypt(String text) {
        return encrypt(text, EncryptType.SHA256);
    }

    /**
     * SHA-512加密字符串
     */
    public static String sha512Encrypt(final String text) {
        return encrypt(text, EncryptType.SHA512);
    }


    /**
     * 加密
     *
     * @param text        加密字符串
     * @param encryptType 加密类型
     */
    public static String encrypt(@NonNull String text, EncryptType encryptType) {
        try {
            MessageDigest digest = MessageDigest.getInstance(encryptType.getValue());
            byte[] bytes = digest.digest(text.getBytes());

            StringBuilder builder = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                String hexStr = Integer.toHexString(b & 0xff);
                if (hexStr.length() == 1) {
                    builder.append("0");
                }
                builder.append(hexStr);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
