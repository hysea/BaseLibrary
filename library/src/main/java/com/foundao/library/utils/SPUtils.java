package com.foundao.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @name create by wukaihong ON 2018/8/27 14:41
 * @desc
 */
public class SPUtils {
    static SharedPreferences sp;

    private static final boolean DEFAULT_BOOLEAN_VALUE = false;

    public static void init(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("xinhua", Context.MODE_PRIVATE);
        }
    }

    /**
     * 保存SharedPreferences数据
     *
     * @param map 键值对，值必须是int，float,boolean,string,long类型，其余类型不支持
     */
    public static synchronized void save(Map<String, Object> map) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object o = entry.getValue();
                if (o instanceof String) {
                    editor.putString(entry.getKey(), (String) o);
                } else if (o instanceof Integer) {
                    editor.putInt(entry.getKey(), (Integer) o);
                } else if (o instanceof Long) {
                    editor.putLong(entry.getKey(), (Long) o);
                } else if (o instanceof Boolean) {
                    editor.putBoolean(entry.getKey(), (Boolean) o);
                } else if (o instanceof Float) {
                    editor.putFloat(entry.getKey(), (Float) o);
                } else {
                    //不支持的类型，不做操作
                    new Throwable("不支持类型").printStackTrace();
                }
            }
            editor.apply();
        }
    }

    /**
     * 保存String型数据
     *
     * @param key   键
     * @param value 值
     */
    public static synchronized void save(String key, String value) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, value).apply();
        }
    }

    /**
     * 保存Int型数据
     *
     * @param key   键
     * @param value 值
     */
    public static synchronized void save(String key, int value) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value).apply();
        }
    }

    /**
     * 保存Boolean型数据
     *
     * @param key   键
     * @param value 值
     */
    public static synchronized void save(String key, boolean value) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value).apply();
        }
    }

    /**
     * 获取存储的字符型数据
     *
     * @param key 键
     * @return value
     */
    public static String getString(String key) {

        return sp == null ? "" : sp.getString(key, "");
    }

    /**
     * 获取存储的布尔型数据
     *
     * @param key 键
     * @return value
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取存储的int型数据
     *
     * @param key 键
     * @return value 默认－1
     */
    public static int getInt(String key) {
        return sp != null ? sp.getInt(key, -1) : -1;
    }

    /**
     * 获取存储的布尔型数据
     *
     * @param key 键
     * @param def 默认值
     * @return value
     */
    public static boolean getBoolean(String key, boolean def) {
        return sp != null && sp.getBoolean(key, def);
    }

    /**
     * 删除对应key的SharedPreferences数据
     *
     * @param key 键
     */
    public static synchronized void remove(String key) {
        if (sp != null) {
            sp.edit().remove(key).apply();
        }
    }

    /**
     * 删除对应key的SharedPreferences数据
     *
     * @param keys 需删除的键的集合
     */
    public static synchronized void remove(String[] keys) {
        if (sp != null) {
            for (String key : keys) {
                sp.edit().remove(key).apply();
            }
        }
    }

    /**
     * 保存List
     *
     * @param key
     * @param datalist
     */
    public static <T> void setDataList(String key, List<T> datalist) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();

            if (null == datalist || datalist.size() == 0) {
                editor.remove(key);
            } else {
                Gson gson = new Gson();
                //转换成json数据，再保存
                String strJson = gson.toJson(datalist);
                editor.putString(key, strJson);
            }
            editor.apply();
        }
    }

    /**
     * 获取List
     *
     * @param key
     * @return
     */
    public static <T> List<T> getDataList(String key, Class<T> cls) {


        List<T> list = new ArrayList<T>();
        String strJson = getString(key);
        if (TextUtils.isEmpty(strJson)) {
            return list;
        }
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static <T> T getObject(Class<T> clazz) {
        String key = getKey(clazz);
        String json = getString(key);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static void putObject(Object object) {
        String key = getKey(object.getClass());
        Gson gson = new Gson();
        String json = gson.toJson(object);
        save(key, json);
    }

    public static void removeObject(Context context, Class<?> clazz){
        remove(getKey(clazz));
    }

    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }


    /**
     * 清空所有SharedPreferences数据
     */
    public static void clear() {
        if (sp != null) {
            sp.edit().clear().apply();
        }
    }

    // boolean related methods
    public static boolean readBoolean(String what) {
        return sp.getBoolean(what, DEFAULT_BOOLEAN_VALUE);
    }

    public static boolean readBoolean(String what, boolean defaultBoolean) {
        return sp.getBoolean(what, defaultBoolean);
    }

    public static void writeBoolean(String where, boolean what) {
        sp.edit().putBoolean(where, what).apply();
    }
}

