package com.foundao.library.json;

import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 将Boolean/String类型相关的用int类型表示，防止解析时，类型转换错误
 * create by hysea on 2019/5/21
 */
public class IntTypeAdapter extends TypeAdapter<Integer> {
    /**
     * 序列化时调用
     */
    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value);
        }
    }

    /**
     * 反序列化时调用
     */
    @Override
    public Integer read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case BOOLEAN:
                return in.nextBoolean() ? 1 : 0;//如果为true则返回为int的1，false返回0.
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return in.nextInt();
            case STRING:
                return toInteger(in.nextString());
            default:
                throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + peek);
        }
    }

    /**
     * "0" 为 false
     * "1" 为 true
     */
    private static int toInteger(String name) {
        if (TextUtils.isEmpty(name)) {
            return 0;
        } else {
            if (name.equalsIgnoreCase("true")) {
                return 1;
            } else if (name.equalsIgnoreCase("false")) {
                return 0;
            } else if (name.equals("1")) {
                return 1;
            } else if (name.equals("0")) {
                return 0;
            }
        }
        return 0;
    }
}
