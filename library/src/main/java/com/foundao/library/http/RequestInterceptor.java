package com.foundao.library.http;

import androidx.annotation.NonNull;

import com.foundao.library.manager.ConfigManager;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器
 * 用于添加参数，如token
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        // 添加公共头部信息
        Map<String, String> headers = ConfigManager.newInstance().getHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(requestBuilder.build());
    }
}
