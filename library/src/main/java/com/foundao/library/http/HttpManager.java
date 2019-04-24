package com.foundao.library.http;

import android.text.TextUtils;

import com.foundao.library.R;
import com.foundao.library.manager.ConfigManager;
import com.foundao.library.utils.ResourceUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求管理
 */
public class HttpManager {

    private static final int TIMEOUT = 15;
    private static volatile HttpManager sHttpManager;
    private Retrofit mRetrofit;

    private HttpManager() {
        init();
    }

    /**
     * 初始化操作，必须先要设置BaseUrl
     */
    private void init() {
        String baseUrl = ConfigManager.newInstance().getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            throw new RuntimeException(ResourceUtils.getString(R.string.empty_base_url));
        }
        // 初始化Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static HttpManager getInstance() {
        if (sHttpManager == null) {
            synchronized (HttpManager.class) {
                if (sHttpManager == null) {
                    sHttpManager = new HttpManager();
                }
            }
        }
        return sHttpManager;
    }

    private OkHttpClient getOkHttpClient() {
        // 日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 初始化OkHttpClient
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                // 处理参数
                .addInterceptor(new RequestInterceptor())
                // 打印日志
                .addInterceptor(loggingInterceptor)
                .build();
    }

    /**
     * 创建请求
     */
    public <T> T createReq(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
