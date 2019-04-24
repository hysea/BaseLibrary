package com.foundao.library.data;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * 对象存储
 * create by hysea on 2019/4/12
 */
public class DataStorage implements IDataStorage {
    private MMKV mmkv;

    public static DataStorage defaultDataStorage() {
        return new DataStorage();
    }

    private DataStorage() {
        mmkv = MMKV.defaultMMKV();
    }


    @Override
    public void putInt(String key, int value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putLong(String key, long value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putFloat(String key, float value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putDouble(String key, double value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putString(String key, String value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putStringSet(String key, Set<String> value) {
        mmkv.encode(key, value);
    }

    @Override
    public void putParcelable(String key, Parcelable value) {
        mmkv.encode(key, value);
    }

    @Override
    public int getInt(String key) {
        return mmkv.decodeInt(key);
    }

    @Override
    public long getLong(String key) {
        return mmkv.decodeLong(key);
    }

    @Override
    public float getFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    @Override
    public double getDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    @Override
    public boolean getBool(String key) {
        return mmkv.decodeBool(key);
    }

    @Override
    public String getString(String key) {
        return mmkv.decodeString(key);
    }

    @Override
    public Set<String> getStringSet(String key) {
        return mmkv.decodeStringSet(key);
    }

    @Override
    public <T extends Parcelable> T getParcelable(String key, Class<T> clazz) {
        return mmkv.decodeParcelable(key, clazz);
    }
}
