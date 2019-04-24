package com.foundao.library.http.exception;

import android.net.ParseException;

import com.foundao.library.R;
import com.foundao.library.utils.LogUtils;
import com.foundao.library.utils.ResourceUtils;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * 统一处理错误/异常
 * Created by hysea on 2018/4/4.
 */
public class ExceptionHandler {
    private static final String TAG = "ExceptionHandler";

    public static final int UN_KNOWN_ERROR = 2000;//未知错误
    private static final int PARSE_ERROR = 2001;//解析错误
    private static final int CONNECT_ERROR = 2003;//网络连接错误
    private static final int TIME_OUT_ERROR = 2004;//网络连接超时
    public static final int NOT_NETWORK_ERROR = 2005; // 无网络状态

    public static ApiException handleException(Throwable e) {
        LogUtils.e(TAG, e.getMessage());
        ApiException ex;
        if (e instanceof HttpException) { // http错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            ex.setMsg(ResourceUtils.getString(R.string.http_error));
            return ex;
        } else if (e instanceof ServerException) { // 服务器返回的错误
            ServerException serverException = (ServerException) e;
            ex = new ApiException(serverException, serverException.getCode());
            ex.setMsg(serverException.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) { // 解析数据错误
            ex = new ApiException(e, PARSE_ERROR);
            ex.setMsg(ResourceUtils.getString(R.string.parse_error));
            return ex;
        } else if (e instanceof ConnectException) { // 连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg(ResourceUtils.getString(R.string.connect_error));
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg(ResourceUtils.getString(R.string.connect_error));
            return ex;
        } else {  //未知错误
            e.printStackTrace();
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg(ResourceUtils.getString(R.string.server_error));
            return ex;
        }
    }
}
