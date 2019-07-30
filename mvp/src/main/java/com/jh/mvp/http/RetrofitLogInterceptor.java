package com.jh.mvp.http;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RetrofitLogInterceptor implements Interceptor {

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i("BaseHttp", "请求地址：" + request.url().toString()+"}分割一下{"+request.toString());
        Log.i("BaseHttp", "请求参数：" + getParams(request.body()));
        Log.i("BaseHttp", "返回内容：" + content);
        Log.i("BaseHttp", "--------------------请求耗时：" + duration + "毫秒--------------------");
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }

    private String getParams(RequestBody body) {
        if (body == null) {
            return "";
        }
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF_8);
                if (charset != null) {
                    return buffer.readString(charset);
                }
            }
        } catch (IOException e) {
            Log.e("BaseHttp", "请求错误：" + e.getMessage().toString());
        }
        return "";
    }
}
