package com.foundao.library.data;

import android.os.Parcelable;

import java.util.List;
import java.util.Set;

/**
 * 数据存储接口
 * create by hysea on 2019/4/12
 */
public interface IDataStorage {
    /**
     * 保存int类型
     */
    void putInt(String key, int value);

    /**
     * 保存long类型
     */
    void putLong(String key, long value);

    /**
     * 保存float类型
     */
    void putFloat(String key, float value);

    /**
     * 保存double类型
     */
    void putDouble(String key, double value);

    /**
     * 保存boolean类型
     */
    void putBoolean(String key, boolean value);

    /**
     * 保存String类型
     */
    void putString(String key, String value);

    /**
     * 保存StringSet类型
     */
    void putStringSet(String key, Set<String> value);

    /**
     * 保存Parcelable对象类型
     */
    void putParcelable(String key, Parcelable value);

    /**
     * 保存List<String>对象类型
     */
    void putStringList(String key, List<String> value);

    /**
     * 获取int类型
     */
    int getInt(String key);

    /**
     * 获取long类型
     */
    long getLong(String key);

    /**
     * 获取float类型
     */
    float getFloat(String key);

    /**
     * 获取double类型
     */
    double getDouble(String key);

    /**
     * 获取boolean类型
     */
    boolean getBool(String key);

    /**
     * 获取String类型
     */
    String getString(String key);

    /**
     * 获取StringSet类型
     */
    Set<String> getStringSet(String key);

    /**
     * 获取Parcelable对象类型
     */
    <T extends Parcelable> T getParcelable(String key, Class<T> clazz);

    /**
     * 获取List<String>对象数据
     */
    List<String> getStringList(String key);
}
