package com.foundao.library.http;

import com.foundao.library.utils.JsonUtils;
import com.foundao.library.utils.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络请求日志打印拦截器：
 * 1、用于调试接口信息，
 * 2、通过它可以拦截网络请求和响应所有信息（请求行、请求头、请求体、响应行、响应头、响应体）
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private static final String TAG = HttpLogger.class.getSimpleName();
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST") || message.startsWith("--> GET") || message.startsWith("--> DELETE")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            if (message.length() < 2000) { // 防止数据过长
                message = JsonUtils.formatJson(JsonUtils.decodeUnicode(message));
            }
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtils.d(TAG, mMessage.toString());
        }
    }
}
